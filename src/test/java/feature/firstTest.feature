Feature: ToDo list

#  As A user add item in TO_DO list
  Scenario: Adding items in TODO list
    Given User is on TODO page
    When User Add item "item" in the text field
    Then check Item should be added in the list

#  As A user I want to add more than 1 item in the list and can mark item as done and can clear the done items and can delete an item
  Scenario: complete and delete item from list
    Given User is on TODO page
    When User add more items in the list
    Then check All Items should be added in the list
    When User mark first item as done
    Then check no. of pending items left
    When User selects Active Tab
    Then check Active Tab shows only pending items
    When User select All tab
    Then check All Tab shows all items
    When User clear completed item from list
    Then check list is updated after items are cleared
    When User delete an item
    Then check deleted items are successfully deleted







