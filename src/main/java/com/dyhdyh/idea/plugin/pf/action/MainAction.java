package com.dyhdyh.idea.plugin.pf.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.plugins.groovy.lang.psi.GroovyPsiElementFactory;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrStatement;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.blocks.GrClosableBlock;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.path.GrMethodCallExpression;

import java.io.File;
import java.util.List;

/**
 * author  dengyuhan
 * created 2018/12/5 11:14
 */
public class MainAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        PsiFile psiFile = getManifestFile(project);
        GroovyPsiElementFactory factory = GroovyPsiElementFactory.getInstance(e.getProject());
        GrStatement dependency = factory.createStatementFromText("compile 'com.dyhdyh:activity:1.0.1'");
        GrClosableBlock dependencies = getDependencies(psiFile);
        if (dependencies != null) {
            dependencies.addStatementBefore(dependency, null);
        }

    }

    public static PsiFile getManifestFile(Project project) {
        String path = project.getBasePath() + File.separator +
                "app" + File.separator +
                "build.gradle";
        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(path);
        if (virtualFile == null) return null;
        return PsiManager.getInstance(project).findFile(virtualFile);
    }

    public static GrClosableBlock getDependencies(PsiFile psiFile) {
        List<GrMethodCallExpression> methodCalls = PsiTreeUtil.getChildrenOfTypeAsList(psiFile.getOriginalElement(), GrMethodCallExpression.class);
        for (GrMethodCallExpression expression : methodCalls) {
            if (expression.getText().startsWith("dependencies")) {
                GrClosableBlock[] arguments = expression.getClosureArguments();
                return arguments[0];
            }
        }
        return null;
    }
}
