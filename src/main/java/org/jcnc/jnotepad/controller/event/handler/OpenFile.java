package org.jcnc.jnotepad.controller.event.handler;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.jcnc.jnotepad.controller.manager.Controller;

import java.io.File;

import static org.jcnc.jnotepad.view.manager.ViewManager.tabPane;

/**
 * 打开文件事件处理器.
 */
public class OpenFile implements EventHandler<ActionEvent> {
    /**
     * 处理打开文件事件.
     *
     * @param event 事件对象
     */
    @Override
    public void handle(ActionEvent event) {
        // 获取控制器
        Controller controller = new Controller();
        // 创建文件选择器
        FileChooser fileChooser = new FileChooser();
        // 显示文件选择对话框，并获取选中的文件
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // 创建打开文件的任务
            Task<Void> openFileTask = new Task<>() {
                @Override
                protected Void call() {
                    // 调用控制器的getText方法，读取文件内容
                    controller.getText(file);
                    // 更新编码标签
                    controller.upDateEncodingLabel(((TextArea) tabPane.getSelectionModel().getSelectedItem().getContent()).getText());
                    return null;
                }
            };

            // 设置任务成功完成时的处理逻辑
            openFileTask.setOnSucceeded(e -> {
                // 处理成功的逻辑
            });

            // 设置任务失败时的处理逻辑
            openFileTask.setOnFailed(e -> {
                // 处理失败的逻辑
            });

            // 创建并启动线程执行任务
            Thread thread = new Thread(openFileTask);
            thread.start();
        }
    }
}
