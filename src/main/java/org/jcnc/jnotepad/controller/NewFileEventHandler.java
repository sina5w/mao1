package org.jcnc.jnotepad.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import org.jcnc.jnotepad.ViewManager;

import static org.jcnc.jnotepad.ViewManager.tabPane;

public class NewFileEventHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Controller controller = new Controller();
        TextArea textArea = new TextArea(); // 创建新的文本编辑区
        Tab tab = new Tab("新建文本 " + ++ViewManager.tabIndex); // 创建新的Tab页
        tab.setContent(textArea);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        controller.updateStatusLabel(textArea);
        // 更新编码信息
        controller.upDateEncodingLabel(textArea.getText()); // 更新文本编码信息
    }
}