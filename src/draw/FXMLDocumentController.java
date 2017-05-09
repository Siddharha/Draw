/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author siddhartha
 */
public class FXMLDocumentController implements Initializable {
    
    private Canvas currentTabCanvas;
    @FXML
    Tab tbAdd;
    @FXML
    TabPane tpMain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeFirstPage();
        onActionPerform();
    }    

    @FXML
    private void mnuSave(Event e){
        FileChooser fileChooser = new FileChooser();
         //Set extension filter
                FileChooser.ExtensionFilter extFilter = 
                        new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showSaveDialog(tpMain.getScene().getWindow());
              if(file != null){
                    try {
                        WritableImage writableImage = new WritableImage((int)currentTabCanvas.getHeight(), (int)currentTabCanvas.getWidth());
                        currentTabCanvas.snapshot(null, writableImage);
                        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                       ex.printStackTrace();
                    }
                }
            
    }
    private void onActionPerform() {
        tbAdd.setOnSelectionChanged(elent->{
        //Create Tabs
      Tab tab = new Tab();
   
      tab.setText("untitled");
      tab.setClosable(true);
      Canvas  c = new Canvas(400,400);
     currentTabCanvas = c;
      ScrollPane sp = new ScrollPane();
     sp.setContent(c);
     sp.setPannable(true);
       tab.setContent(sp);
        drawOnCanvas(c, sp);
      tpMain.getTabs().add(tpMain.getTabs().size()-1,tab);
     tpMain.getSelectionModel().select(tab);
    
        });
        
       tpMain.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
           ScrollPane ss = ((ScrollPane)tpMain.getSelectionModel().getSelectedItem().getContent());
           currentTabCanvas = (Canvas)ss.getContent();
        });
    }

    private void initializeFirstPage() {
         Tab tab = new Tab();
         
      tab.setText("untitled");
      tab.setClosable(true);
       //Add something in Tab
    
       Canvas  c = new Canvas(400,400);
       currentTabCanvas = c;
     c.setCursor(Cursor.DEFAULT);
      ScrollPane sp = new ScrollPane();
     sp.setContent(c);
     sp.setPannable(true);
       tab.setContent(sp);
        drawOnCanvas(c,sp);       
      tpMain.getTabs().add(tpMain.getTabs().size()-1,tab);
     tpMain.getSelectionModel().select(tab);
    }


    private void drawOnCanvas(Canvas c, ScrollPane sp) {
        final GraphicsContext graphicsContext = c.getGraphicsContext2D();
        initDraw(graphicsContext);
        
        c.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            sp.setPannable(false);
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });
         
        c.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });
 
        c.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
          
        });
        
        }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
         
        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
 
        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle
         
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
         
    }
    
}
