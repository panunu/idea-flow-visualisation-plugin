import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.awt.RelativePoint;

public class VisualAnalysisAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        int caretOffset = editor.getCaretModel().getOffset();

        PsiFile currentFile = e.getData(PlatformDataKeys.PSI_FILE);
        PsiElement currentElement = currentFile.findElementAt(caretOffset);
        
        String message = currentElement.getNode().toString();
        
        show(e, message);
    }

    private void show(AnActionEvent e, String message) {
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(DataKeys.PROJECT.getData(e.getDataContext()));

        JBPopupFactory.getInstance()
            .createHtmlTextBalloonBuilder(message, MessageType.INFO, null)
            .setFadeoutTime(15000)
            .createBalloon()
            .show(
                RelativePoint.getCenterOf(statusBar.getComponent()),
                Balloon.Position.atRight
            );
    }
}
