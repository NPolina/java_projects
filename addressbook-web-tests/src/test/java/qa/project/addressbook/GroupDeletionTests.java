package qa.project.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroup();
        gotoGroupPage();
    }

}
