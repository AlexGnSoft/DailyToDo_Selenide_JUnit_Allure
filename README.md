# Tests for Daily To do list application

# Approach to maven project: Page Object + Selenide + JUnit + Allure + Log4j2

TEST CASES:

Test cases #1

Title: Create tasks

Steps:
1. Open application
2. Click on ‘Create your Daily Todo list’ button’
3. Add 5 tasks
4. Click on “Save tasks” button

Expected Result: Tasks are added.

Test cases #2

Title: Start tracking tasks

Steps:
1. Open application
2. Click on ‘Create your Daily Todo list’ button’
3. Add 5 tasks
4. Click on “Save tasks” button
5. Click on all check-boxes
6. Uncheck all selection
7. Click on all the check-boxes again

Expected Result: Check-boxes changes it status from empty to green and a square in a square matrix changes it color as well.

Test cases #3

Title: Update daily tasks

Steps:
1. Open application
2. Click on ‘Create your Daily Todo list’ button’
3. Add 5 tasks
4. Click on “Save tasks” button
5. Click on “Edit button”
6. Update Title name
7. Update Description name of one task
8. Click on “Save tasks” button

Expected Result: Tasks and the title are updated.

Test cases #4

Title: Update name of a tracked task

Steps:
1. Open application
2. Click on ‘Create your Daily Todo list’ button’
3. Add 5 tasks
4. Click on “Save tasks” button
5. Click on the check-box of the first task
6. Click on ‘Edit” button
7. Update name of the 1-st task
8. Click on “Save tasks” button

Expected Result: Check-box of the 1st task changed it status from green (selected) to empty.

Test cases #5

Title: Delete all

Steps:
1. Open application
2. Click on ‘Create your Daily Todo list’ button’
3. Delete title and all tasks
4. Click on “Save tasks” button

Expected Result: Title “Tasks for today” is displayed, tasks are not displayed.

# Run description:

1. Clone public project on your PC
2. Make sure all dependencies are downloaded
3. Run tests locally through the terminal by using the command: mvn test
4. Execute Allure report