import geb.spock.GebReportingSpec

import spock.lang.*

import pages.*

@Stepwise
class MemberCRUDSpec extends GebReportingSpec {

  def "there are no people"() {
    when:
    to ListPage
    then:
    memberRows.size() == 0
  }

  def "add a member"() {
    when:
    newMemberButton.click()
    then:
    at CreatePage
  }

  def "enter the details"() {
    when:
    firstName = "John"
    lastName = "ชาวไร่"
    createButton.click()
    then:
    at ShowPage
  }

  def "check the entered details"() {
    expect:
    firstName == "John"
    lastName == "ชาวไร่"
  }

  def "edit the details"() {
    when:
    editButton.click()
    then:
    at EditPage
    when:
    updateButton.click()
    then:
    at ShowPage
  }

  def "check in listing"() {
    when:
    to ListPage
    then:
    memberRows.size() == 1
    def row = memberRow(0)
    row.firstName == "John"
    row.lastName == "ชาวไร่"
  }

  def "show member"() {
    when:
    memberRow(0).showLink.click()
    then:
    at ShowPage
  }

  def "delete user"() {
    given:
    def deletedId = id
    when:
    withConfirm { deleteButton.click() }
    then:
    at ListPage
    message == "Member $deletedId deleted"
    memberRows.size() == 0
  }
}