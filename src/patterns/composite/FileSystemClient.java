package patterns.composite;

public class FileSystemClient {
    private Folder currentFolder;

    public FileSystemClient(Folder rootFolder) {
        currentFolder = rootFolder;
    }

    public void runCommand(String command) {
        String[] tokens = command.split(" ");
        String commandType = tokens[0];

        switch (commandType) {
            case "dir":
                currentFolder.list();
                break;
            case "cd":
                if (tokens.length < 2) {
                    System.out.println("Missing argument for 'cd' command.");
                } else {
                    String targetFolderName = tokens[1];
                    changeDirectory(targetFolderName);
                }
                break;
            default:
                System.out.println("Unknown command: " + commandType);
                break;
        }
    }

    private void changeDirectory(String folderName) {
        if (folderName.equals("..")) {
            if (currentFolder.getParent() != null) {
                currentFolder = currentFolder.getParent();
            } else {
                System.out.println("Already at the root folder.");
            }
        } else {
            FileSystemComponent component = currentFolder.getComponent(folderName);
            if (component instanceof Folder) {
                currentFolder = (Folder) component;
            } else {
                System.out.println("No such directory: " + folderName);
            }
        }
    }
}
