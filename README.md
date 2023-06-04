# Command List

## help
Displays information about the available commands and their usage

## list
Displays the files and directories in the current directory

## disks
Displays each disk on the device

## cd <directory_path>
Allows you to navigate to the specified directory (".." for the parent directory)

## sd <disk_label>
Switches the paths disk

## cur
Displays the current currentPath

## create <file_or_directory_name>
Creates a new file or directory with the specified name in the current directory. Include file extension to create a file, if no extension is found a directory will be created

## delete <?file_or_directory_path>
Removes the specified file or directory, if no name is provided, the current file or directory will be deleted

## rename <new_name>
Changes the name of the current file or directory

## open <?file_or_directory_path>
Opens the file or directory in the desktops default application, if no currentPath is provided, the current file or directory will be opened

## history
Displays the history of commands

## clear
Clears the log

## exit
Terminates the command line interpreter

# File Editor Command List

## editor
Open the file editor in the current currentPath, file must be read and writeable and not a directory

## preview
Display the contents of the file in the editor

## append <?line_text>
Appends a line to the bottom of the file, if no <line_text> is given, a blank line will be appended

## insert <line_number> <?line_text>
Inserts a line UNDER the given <line_number>, if no <line_text> is given, a blank line will be inserted

## remove <line_number>
Removes the line at the given <line_number>

## wipe
Wipes the file and leaves a blank file

## clear
Clears the log

## exit
Exit the file editor