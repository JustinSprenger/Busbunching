package main;

import database.Database;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tables.Rows;

public class Main extends Application{
	Stage primarystage;
	static Database db1 = new Database("angewandteinfo.spdns.org",3307,"Busbunching","user1","starten123");
	static Database db2 = new Database("angewandteinfo.spdns.org",3307,"Busbunchinguser","user1","starten123");
	private TableView table = new TableView();
	private final ObservableList<Rows> data =
		        FXCollections.observableArrayList(
		            
		        ); 
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primarystage = primaryStage;
		
		final BorderPane borderPane; 
        final Scene scene;
        final Pane pane;
        
        Label l2 = new Label("Buslinie:");
        Label l3 = new Label("Haltestelle:");
        Label l4 = new Label("Richtung:");
        TextField busline = new TextField("122");
        TextField busstation = new TextField("U Osloer Str.");
        TextField direction = new TextField("1");
        Button start = new Button("Start");
        final ComboBox busliste = new ComboBox();
        final ComboBox busstationsliste = new ComboBox();
        final ComboBox directionlist = new ComboBox();
        
        busliste.getItems().addAll(
        	"192"
        );
        directionlist.getItems().addAll(
        	"Richtung 1",
        	"Richtung 2"
        );
        busstationsliste.getItems().addAll(
        	"U Osloer Str.",
        	"U Pankstr.",
        	"Osloer Str./Prinzenallee",
        	"Kindermuseum",
        	"Drontheimer Str.",
        	"Maxstr.",
        	"Exerzierstr.",
        	"Uferstr.",
        	"Ritterlandweg/Soldiner Str."
        );
        
        TableColumn bus = new TableColumn("Buslinie");
        bus.setCellValueFactory(new PropertyValueFactory<Rows,String>("busline"));
        
        TableColumn station = new TableColumn("Haltestelle");
        station.setCellValueFactory(new PropertyValueFactory<Rows,String>("station"));
        
        TableColumn time = new TableColumn("Zeitabstand");
        time.setCellValueFactory(new PropertyValueFactory<Rows,String>("timedelay"));
        
        TableColumn busnum = new TableColumn("Busnummer");
        busnum.setCellValueFactory(new PropertyValueFactory<Rows,String>("busnummer"));
        
        TableColumn or = new TableColumn("Order");
        or.setCellValueFactory(new PropertyValueFactory<Rows,String>("order"));
        
        TableColumn re = new TableColumn("Richtung");
        re.setCellValueFactory(new PropertyValueFactory<Rows,String>("rem"));
        
        table.setItems(data);
        table.getColumns().addAll(bus,station,time,busnum,or,re);
        
        VBox v1 = new VBox();
        v1.getChildren().addAll(l2,busliste,start);
        
        VBox v2 = new VBox();
        v2.getChildren().addAll(l3,busstationsliste);
        
        VBox v3 = new VBox();
        v3.getChildren().addAll(l4,directionlist);
        
        VBox v4 = new VBox();
        v4.getChildren().addAll();
        
        HBox h1 = new HBox();
        h1.getChildren().addAll(v1,v2,v3,v4);
        
        VBox v5 = new VBox();
        v5.getChildren().addAll(h1,table);
        
        scene = new Scene(v5,600,400);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        start.setOnAction((event)-> {
        	data.clear();
        	
        	System.out.println("aktueller Bus");
        	
        	int direct=0;
        	String[][] arr;
        	int buslinien = (int)Integer.valueOf((String)busliste.getValue());
        	String stations = (String) busstationsliste.getValue();
        	int timedelay = 0;
        	int busnummer = 0;
        	int order = 0;
        	String rem = "";
        	
        	System.out.println("Busliniennummer als ID (LDI_ID)");
        	arr = db1.getLdiId(Integer.valueOf((String) busliste.getValue()));
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	
        	System.out.println("Routennummer (Rou_No)");
        	arr = db1.getRouNo(10606661);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Richtungs Nummer (NO)");
        	if(((String) directionlist.getValue()).equals("Richtung 1")){
        		direct = 1;
        	}else if(((String) directionlist.getValue()).equals("Richtung 2")){
        		direct = 2;
        	}
        	arr = db1.getNo(direct, 135);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Haltestellennummer (NP_ID)");
        	arr = db1.getNpid(10606661, 118);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Busnummer (VSCS_ID)");
        	arr = db1.getVscsid(3564322920l, (String) busstationsliste.getValue());
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			busnummer = (int)Integer.valueOf(arr[i][0]);
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Zeitabweichung (Timedelay) und Positionsnummer des Busse (Order)");
        	arr = db2.getTimeDelay(167);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			timedelay = (int)Integer.valueOf(arr[i][0]);
        			order = (int)Integer.valueOf(arr[i][1]);
        		}
        		System.out.println(" ");
        	}
        	
        	System.out.println("Richtung der Linie");
        	arr = db1.getRemark(255);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			rem = arr[i][0];
        		}
        		System.out.println(" ");
        	}

        	
        	Rows r1 = new Rows(buslinien,stations,timedelay,busnummer,order,rem);
        	//data.add(new Rows(buslinien,stations,timedelay,busnummer,order));
        	
        	
        	System.out.println("vorheriger Bus");
        	
        	System.out.println("");
        	arr = db1.getNpIdTo(3564322669l);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	
        	System.out.println(" ");
        	
        	System.out.println("Haltestellennummer (NP_ID)");
        	arr = db1.getNpid(10606661, 118);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Busnummer (VSCS_ID)");
        	arr = db1.getVscsid(3564322921l, (String) busstationsliste.getValue());
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			busnummer = (int)Integer.valueOf(arr[i][0]);
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Zeitabweichung (Timedelay) und Positionsnummer des Busse (Order)");
        	arr = db2.getTimeDelay(895);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			timedelay = (int)Integer.valueOf(arr[i][0]);
        			order = (int)Integer.valueOf(arr[i][1]);
        		}
        		System.out.println(" ");
        	}
        	
        	
        	Rows r2 = new Rows(buslinien,stations,timedelay,busnummer,order,rem);
        	//data.add(new Rows(buslinien,stations,timedelay,busnummer,order));
        	

        	System.out.println("nachfolgender Bus");
        	
        	System.out.println("");
        	arr = db1.getNpIdTo(3564322669l);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	
        	System.out.println(" ");
        	
        	System.out.println("Haltestellennummer (NP_ID)");
        	arr = db1.getNpid(10606661, 118);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Busnummer (VSCS_ID)");
        	arr = db1.getVscsid(3564322920l, (String) busstationsliste.getValue());
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			busnummer = (int)Integer.valueOf(arr[i][0]);
        		}
        		System.out.println(" ");
        	}
        	System.out.println(" ");
        	
        	System.out.println("Zeitabweichung (Timedelay) und Positionsnummer des Busse (Order)");
        	arr = db2.getTimeDelay(3720);
        	for(int i=0;i<arr.length-1;i++){
        		for(int j=0;j<arr[i].length;j++){
        			System.out.print(arr[i][j]+"   ");
        			timedelay = (int)Integer.valueOf(arr[i][0]);
        			order = (int)Integer.valueOf(arr[i][1]);
        		}
        		System.out.println(" ");
        	}
        	
        	
        	Rows r3 = new Rows(buslinien,stations,timedelay,busnummer,order,rem);
        	//data.add(new Rows(buslinien,stations,timedelay,busnummer,order));
        	
        	data.add(r2);
        	data.add(r1);
        	data.add(r3);
        	
        });   
	}
}
