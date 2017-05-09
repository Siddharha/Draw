/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author siddhartha
 */
public class FXMLDocumentController implements Initializable {
    
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

    private void onActionPerform() {
        tbAdd.setOnSelectionChanged(elent->{
        //Create Tabs
      Tab tab = new Tab();
      tab.setText("untitled");
      tab.setClosable(true);
      Canvas  c = new Canvas(400,400);
      ScrollPane sp = new ScrollPane();
     sp.setContent(c);
     sp.setPannable(true);
       tab.setContent(sp);
        drawOnCanvas(c, sp);
      tpMain.getTabs().add(tpMain.getTabs().size()-1,tab);
     tpMain.getSelectionModel().select(tab);
    
        });
    }

    private void initializeFirstPage() {
         Tab tab = new Tab();
      tab.setText("untitled");
      tab.setClosable(true);
       //Add something in Tab
    
       Canvas  c = new Canvas(400,400);
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
