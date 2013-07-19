package pages

import geb.Module

class ListPage extends ScaffoldPage {
  static url = "member/list"

  static at = {
    title ==~ /Member List/
  }

  static content = {
    newMemberButton(to: CreatePage) { $("a", text: "New Member") }
    memberTable { $("div.content table", 0) }
    memberRow { module MemberRow, memberRows[it] }
    memberRows(required: false) { memberTable.find("tbody").find("tr") }
  }
}

class MemberRow extends Module {
  static content = {
    cell { $("td", it) }
    cellText { cell(it).text() }
    cellHrefText{ cell(it).find('a').text() }
    firstName { cellText(0) }
    lastName { cellText(1) }
    showLink(to: ShowPage) { cell(0).find("a") }
  }
}