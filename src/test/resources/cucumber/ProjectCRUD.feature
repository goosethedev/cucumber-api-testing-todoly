Feature: Project Creation, Update and Delete
  Scenario: As an user, I want to create, update and delete a project
    so I can better manage my to-dos.

    Given an user API token provided by Todo.ly

    # Creating a project
    When a POST request is sent to "/api/projects.json" with body
    """
    {
      "Content": "My example project",
      "Icon": 1
    }
    """
    Then the response code should be 200
    And the field "Content" should be "My example project"
    And the field "Id" is saved in the variable "$ID_PROJECT"

    # Updating a project
    When a PUT request is sent to "/api/projects/$ID_PROJECT.json" with body
    """
    {
      "Content": "Updated project name"
    }
    """
    Then the response code should be 200
    And the field "Content" should be "Updated project name"

    # Deleting a project
    When a DELETE request is sent to "/api/projects/$ID_PROJECT.json" with body
    """
    """
    Then the response code should be 200
    And the field "Content" should be "Updated project name"