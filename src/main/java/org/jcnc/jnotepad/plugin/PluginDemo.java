package org.jcnc.jnotepad.plugin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jcnc.jnotepad.tool.LogUtil;
import org.jcnc.jnotepad.tool.UiUtil;
import org.jcnc.jnotepad.ui.dialog.factory.impl.BasicFileChooserFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 插件演示类
 * <p>
 * 用于演示插件加载和执行的界面。
 *
 * @author luke
 */
public class PluginDemo {

    /**
     * 启动插件演示界面
     *
     * @param primaryStage JavaFX的主舞台
     */
    public void start(Stage primaryStage) {
        PluginManager pluginManager = new PluginManager();

        FileChooser fileChooser = BasicFileChooserFactory.getInstance().createFileChooser(
                "选择插件",
                null,
                null,
                new FileChooser.ExtensionFilter("JAR Files", "*.jar")
        );
        Button loadButton = createLoadButton(primaryStage, fileChooser, pluginManager);

        Button executeButton = new Button("执行插件");
        executeButton.setOnAction(event -> pluginManager.executePlugins());

        VBox root = new VBox(10, loadButton, executeButton);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.getIcons().add(UiUtil.getAppIcon());
        primaryStage.setScene(scene);
        primaryStage.setTitle("插件演示");
        primaryStage.show();
    }

    /**
     * 创建加载插件的按钮
     *
     * @param primaryStage JavaFX的主舞台
     * @param fileChooser  文件选择器
     * @param pluginManager 插件管理器
     * @return 加载插件的按钮
     */
    private Button createLoadButton(Stage primaryStage, FileChooser fileChooser, PluginManager pluginManager) {
        Button loadButton = new Button("加载插件");
        loadButton.setOnAction(event -> {
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                if (selectedFile != null) {
                    String pluginFilePath = selectedFile.getAbsolutePath();
                    pluginManager.loadPlugins(pluginFilePath);

                    // 更新插件信息显示
                    displayPluginInfo(primaryStage, pluginManager);
                }
            } catch (Exception ignored) {
                LogUtil.getLogger(this.getClass()).info("未加载插件!");
            }
        });
        return loadButton;
    }

    /**
     * 显示已加载插件的信息
     *
     * @param primaryStage  JavaFX的主舞台
     * @param pluginManager 插件管理器
     */
    private void displayPluginInfo(Stage primaryStage, PluginManager pluginManager) {
        Map<String, List<String>> loadedPluginsByCategory = pluginManager.getLoadedPluginsByCategory();
        VBox infoBox = new VBox();

        loadedPluginsByCategory.forEach((key, pluginNames) -> {
            Label categoryLabel = new Label("类别: " + key);
            VBox categoryInfoBox = new VBox();
            for (String pluginName : pluginNames) {
                Label pluginLabel = new Label("插件名称: " + pluginName);
                categoryInfoBox.getChildren().add(pluginLabel);
            }
            infoBox.getChildren().addAll(categoryLabel, categoryInfoBox);
        });
        Scene infoScene = new Scene(infoBox, 400, 300);
        Stage infoStage = new Stage();
        infoStage.setScene(infoScene);
        infoStage.setTitle("已加载的插件");
        infoStage.initOwner(primaryStage);
        infoStage.show();
    }
}
