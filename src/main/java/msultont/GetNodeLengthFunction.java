package msultont;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import java.util.List;

public class GetNodeLengthFunction extends BrowserFunction {

    private List<Node> nodeLatLon;

    public GetNodeLengthFunction(List<Node> node, Browser browser, String functionName) {
        super(browser, functionName);
        this.nodeLatLon = node;
    }

    @Override
    public Object function(Object[] arguments) {
        Object resultValueLength = null;

        System.out.println ("theJavaFunction() called from javascript with args:");
        for (Object arg : arguments) {
            if (arg == null) {
                System.out.println ("\t-->null");
            } else {
                resultValueLength = nodeLatLon.size();
                System.out.println ("\t-->" + arg.getClass ().getName () + ": " + arg.toString ());
            }
        }

        return resultValueLength;
    }
}
