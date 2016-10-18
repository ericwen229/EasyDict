import main.model.dict.Dict;

import main.view.MainWindow;

import org.junit.Test;

public class UiTest {

    @Test
    public void testUi() {
        Dict d = Dict.createDict();
        d.insert("hello", null);
        d.insert("ello", null);
        d.insert("hllo", null);
        d.insert("helo", null);
        d.insert("hell", null);
        d.insert("xello", null);
        d.insert("hxllo", null);
        d.insert("hexlo", null);
        d.insert("helxo", null);
        d.insert("hellx", null);
        d.insert("xhello", null);
        d.insert("hxello", null);
        d.insert("hexllo", null);
        d.insert("helxlo", null);
        d.insert("hellxo", null);
        d.insert("hellox", null);
        MainWindow.createMainWindow();
        while (true) {}
    }

}
