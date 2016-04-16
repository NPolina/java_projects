package qa.project.addressbook.tests;

import org.testng.annotations.Test;
import qa.project.addressbook.model.GroupData;

/**
 * Created by user on 16.04.2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupMofification();
        app.getGroupHelper().returnToGroupPage();
    }
}
