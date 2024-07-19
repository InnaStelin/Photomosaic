package Photomosaic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class UserInput implements ActionListener {
    private final String inputFileInit;
    private final String tilesDirInit;
    private JLabel labelInputFile, labelTilesDir;
    private JTextField txtInputFile, txtMosaicsDir;
    private JButton buttonInputFile, buttonTilesDir, buttonOk;
    private JCheckBox convertCheck;
    private JFrame frameInput;
    public static String inputFile;
    public static String tilesDir;
    private Boolean doConvert = false;

    UserInput() {

        //Initial path for input image and tiles directory
        inputFileInit = "./resources/Jellyfish.jpg";
        tilesDirInit = "./resources/mosaic_images";
    }
    public void setupAndGetUserInput() {

        frameInput = new JFrame("Photomosaic demo");
        labelInputFile = new JLabel("Select input image file:");
        labelInputFile.setPreferredSize(new Dimension(150,15));
        txtInputFile = new JTextField(25);
        txtInputFile.setText(inputFileInit);
        buttonInputFile = new JButton("...");

        labelTilesDir = new JLabel("Select mosaics directory:");
        labelTilesDir.setPreferredSize(new Dimension(150,15));
        txtMosaicsDir = new JTextField(25);
        txtMosaicsDir.setText(tilesDirInit);
        buttonTilesDir = new JButton("...");

        convertCheck = new JCheckBox("Convert mosaics images");
        buttonOk = new JButton("Ok");

        buttonInputFile.addActionListener(this);
        buttonTilesDir.addActionListener(this);
        buttonOk.addActionListener(this);
        convertCheck.addActionListener(this);

        frameInput.add(labelInputFile);
        frameInput.add(txtInputFile);
        frameInput.add(buttonInputFile);
        frameInput.add(labelTilesDir);
        frameInput.add(txtMosaicsDir);
        frameInput.add(buttonTilesDir);
        frameInput.add(convertCheck);
        frameInput.add(buttonOk);
        frameInput.setSize(540, 160);
        frameInput.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frameInput.setVisible(true);
        frameInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonInputFile) {
            inputFile = selectFileOrDir(inputFileInit);
            txtInputFile.setText(inputFile);
        } else if (e.getSource() == buttonTilesDir) {
            tilesDir = selectFileOrDir(tilesDirInit);
            txtMosaicsDir.setText(tilesDir);
        } else if (e.getSource() == convertCheck) {
            doConvert = !doConvert;
        } else if (e.getSource() == buttonOk) {
            inputFile =  txtInputFile.getText();
            tilesDir = txtMosaicsDir.getText();
            if (doConvert) {
                TileFileMgr tiles = new TileFileMgr();
                tiles.convertToMosaics(tilesDir);  //Scale tiles to desired size
            }
            runDemo();
        }

    }
    private String selectFileOrDir(String initPath) {

        File selectedFile = new File(initPath);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(selectedFile);
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("JPG images", "jpg");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        return selectedFile.getAbsolutePath();
    }
    private void runDemo() {

        var mosaicsDemo  = new MosaicDemo();
        mosaicsDemo.runDemo(inputFile, tilesDir);
    }
}
