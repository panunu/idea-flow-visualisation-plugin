import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class VisualAnalysisAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        System.out.println(e.getPlace());
    }
    
    public void update(AnActionEvent e) {
        System.out.println(e.getPlace());
    }
}
