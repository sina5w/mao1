package org.jcnc.jnotepad.root.left.sidebar.tools;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JNotepadToolBar extends ToolBar {
    private static final JNotepadToolBar INSTANCE = new JNotepadToolBar();

    Button setButton = new Button();

    private JNotepadToolBar() {
        // 创建工具栏上的按钮
        Image image = new Image("tools.png"); // 替换为你的图片文件路径
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        imageView.setScaleX(2.5); // 设置水平缩放比例
        imageView.setScaleY(2.5); // 设置垂直缩放比例
        // 设置缩放比例
        setButton.setGraphic(imageView);

        // 将按钮添加到工具栏
        getItems().addAll(setButton);

    }

    public static JNotepadToolBar getInstance() {
        return INSTANCE;
    }

    public Button getSetButton() {
        return setButton;
    }
}