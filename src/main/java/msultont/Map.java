package msultont;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.util.List;

/*
    This map class will display google maps to draw the line of shortest path by Dijkstra Algorithm.
 */

public class Map{

    private static Browser browser;

    public static void main(List<Node> node) {
        File f = new File("map.html");
        System.out.println(f.toURI().toString());
        if(!f.exists()){
            System.out.println("file not exist! " + f.getAbsolutePath());
            return;
        }
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("Map Browser");
        shell.setLayout(new FillLayout());
        try {
            browser = new Browser(shell, SWT.NONE);
        } catch (SWTError e) {
            System.out.println("Could not instantiate Browser: " + e.getMessage());
            display.dispose();
            return;
        }

        browser.setUrl(f.toURI().toString());

        new GetNodeLengthFunction(node, browser, "getNodeLength");
        new GetNodeLatFunction(node, browser, "getNodeLat");
        new GetNodeLonFunction(node, browser, "getNodeLon");

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();


    }

}
