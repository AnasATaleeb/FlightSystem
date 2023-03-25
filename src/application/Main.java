package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	String [] [] login = { {"Anas" , "anas" }, {"Mamoun","mamoun"} };
	LinkedList<Flight> flightList = new LinkedList<Flight>();
	LinkedList<Passenger> passengerList = new LinkedList<Passenger>();
	ObservableList<Passenger> pas = FXCollections.observableArrayList();
	ObservableList<Flight> fli =  FXCollections.observableArrayList();
	Scene Welcome ;
	@Override
	public void start(Stage primaryStage) {
		try {	
			primaryStage.getIcons().add(new Image("fly.png"));
			primaryStage.setTitle("Palestinian Airline System");
			EnglishLang(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void EnglishLang(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		ImageView logo = new ImageView(new Image("air.png"));
		logo.setFitHeight(400);
		logo.setFitWidth(1200);
		HBox hlogo = new HBox(logo);
		hlogo.setAlignment(Pos.CENTER);
		hlogo.setMaxHeight(400);
		hlogo.setMaxWidth(1200);
		hlogo.setStyle("-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width: 7;");

		Label welcome = new Label("WELCOME TO PALESTINIAN AIRLINE");
		welcome.setStyle("-fx-font-size: 50;");
		welcome.setTextFill(Color.web("silver"));

		VBox v1 = new VBox(40,welcome,hlogo);
		v1.setAlignment(Pos.CENTER);

		Label user = new Label("USER NAME");
		user.setPadding(new Insets(7));
		Label pass = new Label("PASSWORD ");
		pass.setPadding(new Insets(7));
		TextField usert = new TextField();
		PasswordField passt = new PasswordField();
		IconedTextFieled(user, usert);
		IconedTextFieled(pass, passt);

		HBox h1 = new HBox(user,usert);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(pass,passt);
		h2.setAlignment(Pos.CENTER);

		ImageView p =  new ImageView(new Image("log-in.png"));
		p.setFitHeight(50);
		p.setFitWidth(50);
		Button logIN = new Button("lOG IN",p);
		logIN.setEffect(new DropShadow());

		icons(logIN);
		butoonEffect(logIN);

		Label wrongPass = new Label();

		VBox v = new VBox(20,wrongPass, h1,h2,logIN);
		v.setAlignment(Pos.CENTER);


		VBox hh = new VBox(10,v1,v);

		hh.setAlignment(Pos.CENTER);
		pane.setCenter(hh);
		logIN.setOnAction(e ->{
			boolean flag = false;
			for (int j = 0; j < login.length; j++) {
				if(usert.getText().equals(login[j][0]) && passt.getText().equals(login[j][1]) ) {
					flag= true;
					menu(primaryStage);
					wrongPass.setText("");
					usert.clear();
					passt.clear();
				}				
			}
			if(!flag) {
				wrongPass.setText("! Wrong Username or Password !");
				wrongPass.setStyle("-fx-font-size: 15;\n" +
						"-fx-text-fill: silver;");
				usert.clear();
				passt.clear();
			}
		});

		pane.setCenter(hh);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Welcome = new Scene(pane,1535,800);
		primaryStage.setScene(Welcome);
		primaryStage.show();
	}

	private void menu(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("MENU");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setTextFill(Color.web("silver"));

		ImageView r =  new ImageView(new Image("hold (1).png"));
		r.setFitHeight(140);
		r.setFitWidth(140);
		Button read = new Button("Read data",r);
		read.setPrefSize(350, 200);
		icons(read);
		butoonEffect(read);
		read.setEffect(new DropShadow());
		read.setOnAction(e ->{
			readButton(primaryStage);
		});

		ImageView d =  new ImageView(new Image("list (1).png"));
		d.setFitHeight(140);
		d.setFitWidth(140);
		Button disFli = new Button("Display flight’s\n information",d);
		disFli.setPrefSize(350, 200);
		icons(disFli);
		butoonEffect(disFli);
		disFli.setEffect(new DropShadow());
		disFli.setOnAction(e ->{
			displayFlightButton(primaryStage);
		});

		ImageView ds =  new ImageView(new Image("pass (1).png"));
		ds.setFitHeight(140);
		ds.setFitWidth(140);
		Button disPass = new Button("Display \n passengers\n information",ds);
		disPass.setPrefSize(350, 200);
		icons(disPass);
		butoonEffect(disPass);
		disPass.setEffect(new DropShadow());
		disPass.setOnAction(e ->{
			diplayPassenger(primaryStage);
		});

		ImageView a =  new ImageView(new Image("edit (2).png"));
		a.setFitHeight(140);
		a.setFitWidth(140);
		Button add = new Button("Add/Edit flight",a);
		add.setPrefSize(350, 200);
		icons(add);
		butoonEffect(add);
		add.setEffect(new DropShadow());
		add.setOnAction(e ->{
			addButton(primaryStage);
		});

		ImageView re =  new ImageView(new Image("ticket (1).png"));
		re.setFitHeight(140);
		re.setFitWidth(140);
		Button receve = new Button("Reserve a ticket",re);
		receve.setPrefSize(350, 200);
		icons(receve);
		butoonEffect(receve);
		receve.setEffect(new DropShadow());
		receve.setOnAction(e ->{
			receveButton(primaryStage);
		});

		ImageView ca =  new ImageView(new Image("crossed.png"));
		ca.setFitHeight(140);
		ca.setFitWidth(140);
		Button cancelReseve = new Button("Cancel \na reservation",ca);
		cancelReseve.setPrefSize(350, 200);
		icons(cancelReseve);
		butoonEffect(cancelReseve);
		cancelReseve.setEffect(new DropShadow());
		cancelReseve.setOnAction(e ->{
			cancelReseveButton(primaryStage);
		});

		ImageView ct =  new ImageView(new Image("secure (1).png"));
		ct.setFitHeight(140);
		ct.setFitWidth(140);
		Button checkTicket = new Button("Check ticket \nis reserved",ct);
		checkTicket.setPrefSize(350, 200);
		icons(checkTicket);
		butoonEffect(checkTicket);
		checkTicket.setEffect(new DropShadow());
		checkTicket.setOnAction(e ->{
			checkTicketButton(primaryStage);
		});

		ImageView s =  new ImageView(new Image("search (2).png"));
		s.setFitHeight(140);
		s.setFitWidth(140);
		Button serch = new Button("Search for \na specific \npassenge",s);
		serch.setPrefSize(350, 200);
		icons(serch);
		butoonEffect(serch);
		serch.setEffect(new DropShadow());
		serch.setOnAction(e->{
			serchButton(primaryStage);
		});

		ImageView l =  new ImageView(new Image("log-out (2).png"));
		l.setFitHeight(140);
		l.setFitWidth(140);
		Button logOut = new Button("Log Out\n& Quit program",l);
		logOut.setPrefSize(350, 200);
		icons(logOut);
		butoonEffect(logOut);
		logOut.setEffect(new DropShadow());

		logOut.setOnAction(e ->{
			EnglishLang(primaryStage);
			System.exit(0);
		});

		HBox h1 = new HBox(30, read,disFli,disPass);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(30, add,receve,cancelReseve);
		h2.setAlignment(Pos.CENTER);
		HBox h3 = new HBox(30, checkTicket,serch,logOut);
		h3.setAlignment(Pos.CENTER);
		VBox v = new VBox(30 , h1,h2,h3);
		v.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(10 ,welcome, v);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10));
		pane.setCenter(v1);

		Scene scene = new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void serchButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Label welcome = new Label("Search for a specific passenger");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		TableView<Passenger> table = new TableView<Passenger>();

		table.setEditable(false);

		TableColumn <Passenger, Integer>flightNumber = new TableColumn <Passenger, Integer>("Flight Number");
		flightNumber.setMinWidth(198);
		flightNumber.setCellValueFactory(  p -> new SimpleIntegerProperty(p.getValue().getFlightNumber()).asObject());
		flightNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, Integer>ticketNumber = new TableColumn <Passenger, Integer>("Ticket Number");
		ticketNumber.setMinWidth(155);
		ticketNumber.setCellValueFactory( p -> new SimpleIntegerProperty(p.getValue().getTicketNumber()).asObject());
		ticketNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>fullName = new TableColumn<Passenger, String>("Full Name");
		fullName.setMinWidth(230);
		fullName.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getFullName()));
		fullName.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>passportNumber = new TableColumn<Passenger, String>("Passport Number");
		passportNumber.setMinWidth(150);
		passportNumber.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getPassportNumber()));
		passportNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>nationality = new TableColumn<Passenger, String>("Nationality");
		nationality.setMinWidth(200);
		nationality.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getNationality()));
		nationality.setStyle("-fx-alignment: CENTER;");


		TableColumn <Passenger, String>birthdate = new TableColumn<Passenger, String>("Birth Date");
		birthdate.setMinWidth(198);
		birthdate.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getBirthdate()));
		birthdate.setStyle("-fx-alignment: CENTER;");


		Label flight = new Label("Passenger Name : ");
		flight.setPadding(new Insets(7));
		TextField flightt = new TextField();
		IconedTextFieled(flight, flightt);
		HBox h1 = new HBox(flight,flightt);
		h1.setAlignment(Pos.CENTER);


		ObservableList<Passenger> data = FXCollections.observableArrayList();

		table.setItems(data);
		table.setMaxWidth(1150);
		table.setMinHeight(250);
		table.getColumns().addAll(flightNumber,ticketNumber,fullName,passportNumber,nationality,birthdate);

		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 100);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});


		ImageView p =  new ImageView(new Image("search (3).png"));
		p.setFitHeight(70);
		p.setFitWidth(70);
		Button serch = new Button("Serch passenger",p);
		serch.setPrefSize(280, 100);
		icons(serch);
		butoonEffect(serch);
		serch.setEffect(new DropShadow());
		serch.setOnAction(e ->{
			if(pas != null) {
				pas.clear();
				table.getItems().clear();
			}
			try {
				pas = serch(flightt.getText().trim());
			} catch (Exception e2) {
				wrong.setText("Error: Input wrong!");
			}
			if(pas != null && !pas.isEmpty()) {
				for (int i = 0; i < pas.size(); i++) {
					table.getItems().add(pas.get(i));
				}
				wrong.setText("");
			}
			else	
				wrong.setText("Error: There is no passenger with this name !");
		});

		HBox butt = new HBox(30,serch,back);
		butt.setAlignment(Pos.CENTER);

		VBox v = new VBox(30,welcome,wrong , table ,h1 ,butt);
		v.setAlignment(Pos.CENTER);
		pane.setCenter(v);

		pane.setPadding(new Insets(30));
		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private ObservableList<Passenger> serch(String data) {
		ObservableList<Passenger> p = FXCollections.observableArrayList();
		Node<Flight> curr = flightList.getHead();
		if (curr == null)
			return null;
		for (  ; curr != null; curr = curr.getNext()) {
			Node<Passenger> curr2 = curr.getData().getPassList().getHead();
			for (  ; curr2 != null; curr2 = curr2.getNext()) {
				if (curr2.getData().getFullName().equals(data)){
					p.add(curr2.getData());
				}
			}
		}
		return p;
	}

	private void checkTicketButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Check ticket is receve");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label flightNumber = new Label("Flight Number :    ");
		flightNumber.setPadding(new Insets(17));

		TextField tflightNumber = new TextField();

		tflightNumber.setMinHeight(56.4);
		tflightNumber.setMinWidth(560);
		IconedTextFieled(flightNumber,tflightNumber);

		HBox h1 = new HBox(flightNumber,tflightNumber);
		h1.setAlignment(Pos.CENTER);

		Label pass = new Label("Passenger Name : ");
		pass.setPadding(new Insets(17));

		TextField tpass = new TextField();

		tpass.setMinHeight(56.4);
		tpass.setMinWidth(560);
		IconedTextFieled(pass,tpass);

		HBox h3 = new HBox(pass,tpass);
		h3.setAlignment(Pos.CENTER);


		Label massege = new Label("Massege :              ");
		massege.setPadding(new Insets(17));

		TextField tmassege = new TextField();

		tmassege.setMinHeight(56.4);
		tmassege.setMinWidth(560);
		IconedTextFieled(massege,tmassege);

		HBox h2 = new HBox(massege,tmassege);
		h2.setAlignment(Pos.CENTER);

		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 85);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});

		ImageView r =  new ImageView(new Image("secure (1).png"));
		r.setFitHeight(70);
		r.setFitWidth(70);
		Button receve = new Button("Chech Tickt is reseve",r);
		receve.setPrefSize(330, 85);
		icons(receve);
		butoonEffect(receve);
		receve.setEffect(new DropShadow());
		receve.setOnAction(e ->{
			if(!tflightNumber.getText().equals("")) {
				Boolean flag =true;
				Node<Flight> curr = flightList.getHead();
				for (  ; curr != null; curr = curr.getNext()) {
					if (curr.getData().getFlightNumber() == Integer.parseInt(tflightNumber.getText()) ){
						Node<Passenger> curr2 = curr.getData().getPassList().getHead();
						for (  ; curr2 != null; curr2 = curr2.getNext()) {
							if(curr2.getData().getFullName().equals(tpass.getText())) {
								tmassege.setText("The ticket number is already receve in flight -> " + curr.getData().getFlightNumber());
								flag = false;
								break;
							}
						}
					}
				}
				if(flag)
					tmassege.setText("The ticket number is not receve.");
			}
		});

		HBox hButton = new HBox(30,receve,back);
		hButton.setAlignment(Pos.CENTER);

		VBox v = new VBox(40,h1,h3,h2);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);

		pane.setBottom(hButton);
		pane.setPadding(new Insets(25));

		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void cancelReseveButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15));
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Cancel receve ticket");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		TableView<Passenger> table = new TableView<Passenger>();

		table.setEditable(false);

		TableColumn <Passenger, Integer>flightNumber = new TableColumn <Passenger, Integer>("Flight Number");
		flightNumber.setMinWidth(198);
		flightNumber.setCellValueFactory(  p -> new SimpleIntegerProperty(p.getValue().getFlightNumber()).asObject());
		flightNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, Integer>ticketNumber = new TableColumn <Passenger, Integer>("Ticket Number");
		ticketNumber.setMinWidth(155);
		ticketNumber.setCellValueFactory( p -> new SimpleIntegerProperty(p.getValue().getTicketNumber()).asObject());
		ticketNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>fullName = new TableColumn<Passenger, String>("Full Name");
		fullName.setMinWidth(230);
		fullName.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getFullName()));
		fullName.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>passportNumber = new TableColumn<Passenger, String>("Passport Number");
		passportNumber.setMinWidth(150);
		passportNumber.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getPassportNumber()));
		passportNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>nationality = new TableColumn<Passenger, String>("Nationality");
		nationality.setMinWidth(200);
		nationality.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getNationality()));
		nationality.setStyle("-fx-alignment: CENTER;");


		TableColumn <Passenger, String>birthdate = new TableColumn<Passenger, String>("Birth Date");
		birthdate.setMinWidth(198);
		birthdate.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getBirthdate()));
		birthdate.setStyle("-fx-alignment: CENTER;");

		ObservableList<Passenger> data = FXCollections.observableArrayList();

		Label pass = new Label("Passenger Name ");
		pass.setPadding(new Insets(7));
		TextField tpass = new TextField();
		IconedTextFieled(pass, tpass);
		HBox h1 = new HBox(pass,tpass);
		h1.setAlignment(Pos.CENTER);

		Label flight = new Label("Flight Number ");
		flight.setPadding(new Insets(7));
		TextField flightt = new TextField();
		IconedTextFieled(flight, flightt);
		HBox h2 = new HBox(flight,flightt);
		h2.setAlignment(Pos.CENTER);


		HBox h4 = new HBox(15,h1,h2);
		h4.setAlignment(Pos.CENTER);


		table.setItems(data);
		table.setMaxWidth(1150);
		table.setMinHeight(250);
		table.getColumns().addAll(flightNumber,ticketNumber,fullName,passportNumber,nationality,birthdate);


		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 85);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});

		ImageView s =  new ImageView(new Image("search (4).png"));
		s.setFitHeight(70);
		s.setFitWidth(70);
		Button serch = new Button("Serch ticket",s);
		serch.setPrefSize(280, 85);
		icons(serch);
		butoonEffect(serch);
		serch.setEffect(new DropShadow());
		serch.setOnAction(e ->{
			table.getItems().clear();
			try {
				if(!flightt.getText().equals("")) {
					Boolean flag = true;
					Node<Flight> curr = flightList.getHead();
					for (  ; curr != null; curr = curr.getNext()) {
						if (curr.getData().getFlightNumber() ==  Integer.parseInt(flightt.getText())){
							if(!tpass.getText().equals("")) {
								Node<Passenger> curr2 = curr.getData().getPassList().getHead();
								for (  ; curr2 != null; curr2 = curr2.getNext()) {
									if(curr2.getData().getFullName().equals(tpass.getText()))
										data.add(curr2.getData());
								}
								table.setItems(data);
								flag = false;
								wrong.setText("Tickets Found");
							} 
						}
					}
					if(flag) {
						wrong.setText("Ticket Not Found");
					}
				}
			}catch (Exception e1) {
				wrong.setText(e1.getMessage() + " ");
			}
		});

		ImageView d =  new ImageView(new Image("delete.png"));
		d.setFitHeight(70);
		d.setFitWidth(70);
		Button delete = new Button("Cancel receve ticket",d);
		delete.setPrefSize(330, 85);
		icons(delete);
		butoonEffect(delete);
		delete.setEffect(new DropShadow());
		delete.setOnAction(e ->{
			ObservableList<Passenger> selectedPassenger = table.getSelectionModel().getSelectedItems();	
			Node<Flight> curr = flightList.getHead();
			for (  ; curr != null; curr = curr.getNext()) {
				Node<Passenger> curr2 = curr.getData().getPassList().getHead();
				for (  ; curr2 != null; curr2 = curr2.getNext()) {
					if(curr2.getData().equals(table.getSelectionModel().getSelectedItem())) {
						curr.getData().getPassList().delete(curr2.getData());
						table.getItems().removeAll(selectedPassenger);
						writeFlightFile();
						writePassengerFile();
					}
				}
			}
		});

		HBox hButton = new HBox(20,serch,delete,back);
		hButton.setAlignment(Pos.CENTER);

		VBox v = new VBox(30,wrong,table,h4,hButton);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);

		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void receveButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15));
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Receve a ticket");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label Mainwrong = new Label();
		Mainwrong.setStyle("-fx-font-size: 18;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");


		SplitPane split = new SplitPane();
		split.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		// -------------------------------------------------- Flight Pane

		BorderPane p1 = new BorderPane();
		Label welcome1 = new Label("Flight Information");
		welcome1.setStyle("-fx-font-size: 20;");
		welcome1.setPadding(new Insets(15));
		welcome1.setTextFill(Color.web("silver"));
		p1.setTop(welcome1);
		p1.setAlignment(welcome1, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		Label flightNumber = new Label("Flight Number :");
		flightNumber.setPadding(new Insets(10));
		Label airlineName = new Label("Airline Name :  ");
		airlineName.setPadding(new Insets(10));
		Label source = new Label("Source :            ");
		source.setPadding(new Insets(10));
		Label destination = new Label("Destination :    ");
		destination.setPadding(new Insets(10));
		Label capacity = new Label("Capacity :         ");
		capacity.setPadding(new Insets(10));

		TextField tflightNumber = new TextField();
		TextField tflightNumber1 = new TextField();
		TextField tairlineName = new TextField();
		TextField tSource = new TextField();
		TextField tdestination = new TextField();
		TextField tcapacity = new TextField();

		tairlineName.setEditable(false);
		tSource.setEditable(false);
		tdestination.setEditable(false);
		tcapacity.setEditable(false);

		tflightNumber.setMinHeight(42);
		tflightNumber.setMinWidth(460);
		tairlineName.setMinHeight(42);
		tairlineName.setMinWidth(460);
		tSource.setMinHeight(42);
		tSource.setMinWidth(460);
		tdestination.setMinHeight(42);
		tdestination.setMinWidth(460);
		tcapacity.setMinHeight(42);
		tcapacity.setMinWidth(460);


		IconedTextFieled(flightNumber,tflightNumber);
		IconedTextFieled(airlineName,tairlineName);
		IconedTextFieled(source,tSource);
		IconedTextFieled(destination,tdestination);
		IconedTextFieled(capacity,tcapacity);

		HBox h1 = new HBox(flightNumber,tflightNumber);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(airlineName,tairlineName);
		h2.setAlignment(Pos.CENTER);
		HBox h3 = new HBox(source,tSource);
		h3.setAlignment(Pos.CENTER);
		HBox h4 = new HBox(destination,tdestination);
		h4.setAlignment(Pos.CENTER);
		HBox h5 = new HBox(capacity,tcapacity);
		h5.setAlignment(Pos.CENTER);

		Label passList = new Label("Passenger List :");
		passList.setPadding(new Insets(35));
		TextArea  tpassList = new TextArea();
		tpassList.setMaxHeight(92);
		tpassList.setMaxWidth(400);
		tpassList.setEditable(false);
		IconedTextFieled(passList,tpassList);
		tpassList.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.7;");

		HBox h6 = new HBox(passList,tpassList);
		h6.setAlignment(Pos.CENTER);

		ImageView s =  new ImageView(new Image("search (4).png"));
		s.setFitHeight(30);
		s.setFitWidth(30);
		Button search = new Button("Search Flight",s);
		search.setPrefSize(250, 40);

		icons(search);
		butoonEffect(search);
		search.setEffect(new DropShadow());


		VBox v = new VBox(20 ,h1,h2,h3,h4,h5,h6,search);
		v.setAlignment(Pos.CENTER);

		VBox vW = new VBox(20 ,wrong,v);
		vW.setAlignment(Pos.CENTER);
		p1.setCenter(vW);


		// -------------------------------------------------- Passenger Pane

		BorderPane p2 = new BorderPane();
		Label welcome2 = new Label("Passenger Information");
		welcome2.setStyle("-fx-font-size: 20;");
		welcome2.setPadding(new Insets(15));
		welcome2.setTextFill(Color.web("silver"));
		p2.setTop(welcome2);
		p2.setAlignment(welcome2, Pos.CENTER);

		Label flightNumber1 = new Label("Flight Number :     ");
		flightNumber1.setPadding(new Insets(10));
		Label ticketNumber = new Label("Ticket Number :      ");
		ticketNumber.setPadding(new Insets(10));
		Label fullName = new Label("Full Name :              ");
		fullName.setPadding(new Insets(10));
		Label passportNumber = new Label("Passport Number :");
		passportNumber.setPadding(new Insets(10));
		Label nationality = new Label("Nationality :           ");
		nationality.setPadding(new Insets(10));
		Label birthdate = new Label("Birthdate :              ");
		birthdate.setPadding(new Insets(10));


		TextField tticketNumber = new TextField();
		tticketNumber.setEditable(false);
		TextField tfullName = new TextField();
		TextField tPassNum = new TextField();
		TextField tNatio = new TextField();
		TextField tBirth = new TextField();
		tflightNumber1.setMinHeight(42);
		tflightNumber1.setMinWidth(460);
		tticketNumber.setMinHeight(42);
		tticketNumber.setMinWidth(460);
		tfullName.setMinHeight(42);
		tfullName.setMinWidth(460);
		tPassNum.setMinHeight(42);
		tPassNum.setMinWidth(460);
		tNatio.setMinHeight(42);
		tNatio.setMinWidth(460);
		tBirth.setMinHeight(42);
		tBirth.setMinWidth(460);


		IconedTextFieled(flightNumber1,tflightNumber1);
		IconedTextFieled(ticketNumber,tticketNumber);
		IconedTextFieled(fullName,tfullName);
		IconedTextFieled(passportNumber,tPassNum);
		IconedTextFieled(nationality,tNatio);
		IconedTextFieled(birthdate,tBirth);

		HBox h7 = new HBox(flightNumber1,tflightNumber1);
		h7.setAlignment(Pos.CENTER);
		HBox h8 = new HBox(ticketNumber,tticketNumber);
		h8.setAlignment(Pos.CENTER);
		HBox h9 = new HBox(fullName,tfullName);
		h9.setAlignment(Pos.CENTER);
		HBox h10 = new HBox(passportNumber,tPassNum);
		h10.setAlignment(Pos.CENTER);
		HBox h11 = new HBox(nationality,tNatio);
		h11.setAlignment(Pos.CENTER);
		HBox h12 = new HBox(birthdate,tBirth);
		h12.setAlignment(Pos.CENTER);

		Label wrong2 = new Label();
		wrong2.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		ImageView s1 =  new ImageView(new Image("search (4).png"));
		s1.setFitHeight(30);
		s1.setFitWidth(30);
		Button search1 = new Button("Search Passenger",s1);
		search.setPrefSize(250, 40);

		icons(search1);
		butoonEffect(search1);
		search1.setEffect(new DropShadow());

		VBox v1 = new VBox(25 ,h7,h8,h9,h10,h11,h12,search1);
		v1.setAlignment(Pos.CENTER);

		VBox vW1 = new VBox(20 ,wrong2,v1);
		vW1.setAlignment(Pos.CENTER);
		p2.setCenter(vW1);

		//--------------------------------
		split.getItems().addAll(p1,p2);


		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 85);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});

		ImageView r =  new ImageView(new Image("ticket (3).png"));
		r.setFitHeight(70);
		r.setFitWidth(70);
		Button receve = new Button("Receve ticket",r);
		receve.setPrefSize(280, 85);
		icons(receve);
		butoonEffect(receve);
		receve.setEffect(new DropShadow());
		receve.setOnAction(e ->{
			try {
				Node<Flight> curr = flightList.getHead();
				boolean fa = true;
				for (  ; curr != null; curr = curr.getNext()) {
					if(curr.getData().getFlightNumber() == Integer.parseInt(tflightNumber1.getText()) ) {
						fa=false;
						Node<Passenger> curr2 = curr.getData().getPassList().getHead();
						boolean f = true;
						for (  ; curr2 != null; curr2 = curr2.getNext()) {
							if(curr2.getData().getFullName().equals(tfullName.getText())) {
								Mainwrong.setText("The Name of passenger is already in passenger list !");
								f = false;
								break;
							}
						}
						if(f) {
							if(isFull(curr)) { // check capacity if is full.
								if(!tflightNumber1.getText().isBlank()) {
									if(!tticketNumber.getText().isBlank()) {
										if(!tfullName.getText().isBlank()) {
											if(!tPassNum.getText().isBlank()) {
												if(!tNatio.getText().isBlank()) {
													if(!tBirth.getText().isBlank()) {	
														String [] tok = tBirth.getText().split("/");
														Date date =  new Date(Integer.parseInt(tok[2])-1900, Integer.parseInt(tok[1])-1, Integer.parseInt(tok[0]));
														Passenger p = new Passenger(Integer.parseInt(tflightNumber1.getText()), Integer.parseInt(tticketNumber.getText()),
																tfullName.getText(),tPassNum.getText(), tNatio.getText(), date );
														curr.getData().getPassList().insert(p);
														Mainwrong.setText("The ticket is receved.");	
														writeFlightFile();
														writePassengerFile();
													}
													else
														Mainwrong.setText("Error : Birth date filed is blank !!");
												}
												else
													Mainwrong.setText("Error : Nationality filed is blank !!");
											}
											else
												Mainwrong.setText("Error : Passport number filed is blank!!");
										}
										else
											Mainwrong.setText("Error : Full name filed is blank !!");
									}else
										Mainwrong.setText("Error : Ticket number filed is blank !!");
								}
								else
									Mainwrong.setText("Error : Flight number filed is blank !!");
							}
							else
								Mainwrong.setText("The Capacity of flight is full.");
						}
					}
				}
				if(fa)
					Mainwrong.setText("The flight is not found");
			}catch (NumberFormatException c) { 
				Mainwrong.setText(" Error Input "+ c.getMessage());
			}
		});

		search1.setOnAction(e ->{ //search in passenger pane
			try {
				boolean flag = true;
				// search Flight
				Node<Flight> curr = flightList.getHead();
				for (  ; curr != null; curr = curr.getNext()) {
					if(!tfullName.getText().isBlank() && tflightNumber1.getText().isBlank()) {
						Node<Passenger> curr2 = curr.getData().getPassList().getHead();
						for (  ; curr2 != null; curr2 = curr2.getNext()) {
							if (curr2.getData().getFullName().equals(tfullName.getText())){
								tflightNumber.setText(curr.getData().getFlightNumber() + "");
								tairlineName.setText(curr.getData().getAirlineName());
								tSource.setText(curr.getData().getSource());
								tdestination.setText(curr.getData().getDestination());
								tcapacity.setText(curr.getData().getCapacity() +"");
								tpassList.setText(curr.getData().getPassList().traverseToString());
								tPassNum.setText(curr2.getData().getPassportNumber());
								tNatio.setText(curr2.getData().getNationality());
								tBirth.setText(curr2.getData().getBirthdate());
								tflightNumber1.setText(curr2.getData().getFlightNumber() +"");
								tticketNumber.setText(curr2.getData().getTicketNumber()+"");
							}
						}
					}
					else {
						if (curr.getData().getFlightNumber() ==  Integer.parseInt(tflightNumber1.getText())){
							tflightNumber.setText(curr.getData().getFlightNumber() + "");
							tairlineName.setText(curr.getData().getAirlineName());
							tSource.setText(curr.getData().getSource());
							tdestination.setText(curr.getData().getDestination());
							tcapacity.setText(curr.getData().getCapacity() +"");
							tpassList.setText(curr.getData().getPassList().traverseToString());
							flag = false;
							Node<Passenger> curr2 = curr.getData().getPassList().getHead();
							int max =  0;
							for (  ; curr2 != null; curr2 = curr2.getNext()) {
								if(curr2.getData().getTicketNumber() > max) {
									max = curr2.getData().getTicketNumber();									
								}
							}
							tticketNumber.setText((max+1) + "");
							tPassNum.setText("");
							tfullName.setText("");
							tNatio.setText("");
							tBirth.setText("");
							wrong.setText("Flight Found");
						}
					}
				}
				// set text Filed "" in flightPane
				if(flag) {
					wrong.setText("Flight Not Found");
					tflightNumber.setText("");
					tairlineName.setText("");
					tSource.setText("");
					tdestination.setText("");
					tcapacity.setText("");
					tpassList.setText("");
				}

			}catch (Exception c) {
				Mainwrong.setText(" Error Input "+ c.getMessage());
			}
		});

		search.setOnAction(e ->{ //search in flight pane
			try {
				Node<Flight> curr = flightList.getHead();
				boolean flag = true;
				for (  ; curr != null; curr = curr.getNext()) {
					if (curr.getData().getFlightNumber() ==  Integer.parseInt(tflightNumber.getText())){
						tflightNumber.setText(curr.getData().getFlightNumber() + "");
						tflightNumber1.setText(curr.getData().getFlightNumber() + "");
						tairlineName.setText(curr.getData().getAirlineName());
						tSource.setText(curr.getData().getSource());
						tdestination.setText(curr.getData().getDestination());
						tcapacity.setText(curr.getData().getCapacity() +"");
						tpassList.setText(curr.getData().getPassList().traverseToString());
						flag = false;
						Node<Passenger> curr2 = curr.getData().getPassList().getHead();
						int max =  0;
						for (  ; curr2 != null; curr2 = curr2.getNext()) {
							if(curr2.getData().getTicketNumber() > max) {
								max = curr2.getData().getTicketNumber();									
							}
						}
						tticketNumber.setText((max+1) + "");
						wrong.setText("Flight Found");
					}
				}
				if(flag) {
					wrong.setText("Flight Not Found");
					tflightNumber.setText("");
					tairlineName.setText("");
					tSource.setText("");
					tdestination.setText("");
					tcapacity.setText("");
					tpassList.setText("");
				}
			}catch (NumberFormatException c) {
				Mainwrong.setText(" Error Input "+c.getMessage());
			}
		});

		HBox hButton = new HBox(20,receve,back);
		hButton.setAlignment(Pos.CENTER);
		VBox vpane = new VBox(10, split,Mainwrong,hButton);
		vpane.setAlignment(Pos.CENTER);
		pane.setBottom(vpane);

		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private boolean isFull(Node<Flight> curr) {
		Node<Passenger> curr2 = curr.getData().getPassList().getHead();
		int size =0;
		for (  ; curr2 != null; curr2 = curr2.getNext()) {
			size +=1;
		}
		if(size == curr.getData().getCapacity())
			return false;
		else
			return true;
	}

	private void addButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Add / Edit Flight");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label flightNumber = new Label("Flight Number :");
		flightNumber.setFont(new Font("Times New Roman",30));
		flightNumber.setPadding(new Insets(17));
		Label airlineName = new Label("Airline Name :  ");
		airlineName.setFont(new Font("Times New Roman",30));
		airlineName.setPadding(new Insets(17));
		Label source = new Label("Source :            ");
		source.setFont(new Font("Times New Roman",30));
		source.setPadding(new Insets(17));
		Label destination = new Label("Destination :    ");
		destination.setFont(new Font("Times New Roman",30));
		destination.setPadding(new Insets(17));
		Label capacity = new Label("Capacity :         ");
		capacity.setFont(new Font("Times New Roman",30));
		capacity.setPadding(new Insets(17));

		TextField tflightNumber = new TextField();
		TextField tairlineName = new TextField();
		TextField tSource = new TextField();
		TextField tdestination = new TextField();
		TextField tcapacity = new TextField();


		tflightNumber.setMinHeight(56.4);
		tflightNumber.setMinWidth(860);
		tairlineName.setMinHeight(56.4);
		tairlineName.setMinWidth(860);
		tSource.setMinHeight(56.4);
		tSource.setMinWidth(860);
		tdestination.setMinHeight(56.4);
		tdestination.setMinWidth(860);
		tcapacity.setMinHeight(56.4);
		tcapacity.setMinWidth(860);


		IconedTextFieled(flightNumber,tflightNumber);
		IconedTextFieled(airlineName,tairlineName);
		IconedTextFieled(source,tSource);
		IconedTextFieled(destination,tdestination);
		IconedTextFieled(capacity,tcapacity);

		HBox h1 = new HBox(flightNumber,tflightNumber);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(airlineName,tairlineName);
		h2.setAlignment(Pos.CENTER);
		HBox h3 = new HBox(source,tSource);
		h3.setAlignment(Pos.CENTER);
		HBox h4 = new HBox(destination,tdestination);
		h4.setAlignment(Pos.CENTER);
		HBox h5 = new HBox(capacity,tcapacity);
		h5.setAlignment(Pos.CENTER);

		Label passList = new Label("Passenger List :");
		passList.setPadding(new Insets(67));
		passList.setFont(new Font("Times New Roman",30));
		TextArea  tpassList = new TextArea();
		tpassList.setMaxHeight(156);
		tpassList.setMinWidth(760);
		tpassList.setEditable(false);
		IconedTextFieled(passList,tpassList);
		tpassList.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 16;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.7;");

		HBox h6 = new HBox(passList,tpassList);
		h6.setAlignment(Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");


		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 100);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});

		ImageView a =  new ImageView(new Image("plus.png"));
		a.setFitHeight(70);
		a.setFitWidth(70);
		Button add = new Button("Add Flight",a);
		add.setPrefSize(280, 100);
		icons(add);
		butoonEffect(add);
		add.setEffect(new DropShadow());
		add.disableProperty().set(true);
		add.setOnAction(e ->{
			try {			
				Flight f = new Flight(Integer.parseInt(tflightNumber.getText()), tairlineName.getText(), tSource.getText(), tdestination.getText(), Integer.parseInt(tcapacity.getText()));
				flightList.insert(f);
				wrong.setText("Flight add succesfuly");	
				tflightNumber.setText("");
				tairlineName.setText("");
				tSource.setText("");
				tdestination.setText("");
				tcapacity.setText("");
				tpassList.setText("");	
				writeFlightFile();
			}catch (Exception e1) {
				wrong.setText("Error in add Flight : " + e1.getMessage());
			}
		});

		ImageView u =  new ImageView(new Image("synchronize.png"));
		u.setFitHeight(70);
		u.setFitWidth(70);
		Button update = new Button("Update Flight",u);
		update.setPrefSize(280, 100);
		icons(update);
		butoonEffect(update);
		update.setEffect(new DropShadow());
		update.disableProperty().set(true);
		update.setOnAction(e ->{
			try {
				if(!tflightNumber.getText().isBlank()) {
					Boolean flag = true;
					Node<Flight> curr = flightList.getHead();
					for (  ; curr != null; curr = curr.getNext()) {
						if (curr.getData().getFlightNumber() ==  Integer.parseInt(tflightNumber.getText().trim())){
							Node<Passenger> curr2 = curr.getData().getPassList().getHead();
							int size =0;
							for (  ; curr2 != null; curr2 = curr2.getNext()) {
								size +=1;
							}
							if(Integer.parseInt(tcapacity.getText()) < size) {
								wrong.setText("Error : The number of passenger is biger than capacity you entered !!");
								flag = false;
							}
							else {
								curr.getData().setCapacity(Integer.parseInt(tcapacity.getText().trim()));
								curr.getData().setAirlineName(tairlineName.getText());
								curr.getData().setSource(tSource.getText());
								curr.getData().setDestination(tdestination.getText());
								writeFlightFile();
								tSource.setText("");
								tdestination.setText("");
								tairlineName.setText("");
								tflightNumber.setText("");
								tcapacity.setText("");
								tpassList.setText("");
								wrong.setText("Flight Update Succesfuly");
								flag = false;
							}
							break;
						}
					}
					if(flag) {
						wrong.setText("Flight you need update is not Found");
					}
				}
			}catch (Exception e1) {
				System.out.println(e1);
				wrong.setText("Error in update Flight : " + e1.getMessage());
			}
		});


		ImageView s =  new ImageView(new Image("search (4).png"));
		s.setFitHeight(70);
		s.setFitWidth(70);
		Button search = new Button("Search Flight",s);
		search.setPrefSize(280, 100);
		icons(search);
		butoonEffect(search);
		search.setEffect(new DropShadow());
		search.setOnAction(e ->{
			try {
				if(tflightNumber.getText() != "") {
					Boolean flag = true;
					Node<Flight> curr = flightList.getHead();
					for (  ; curr != null; curr = curr.getNext()) {
						if (curr.getData().getFlightNumber() ==  Integer.parseInt(tflightNumber.getText())){
							tflightNumber.setText(curr.getData().getFlightNumber() + "");
							tairlineName.setText(curr.getData().getAirlineName());
							tSource.setText(curr.getData().getSource());
							tdestination.setText(curr.getData().getDestination());
							tcapacity.setText(curr.getData().getCapacity() +"");
							tpassList.setText(curr.getData().getPassList().traverseToString());
							flag = false;
							wrong.setText("Flight Found");
							add.disableProperty().set(true);
							update.disableProperty().set(false);
						}
					}
					if(flag) {
						wrong.setText("Flight Not Found");
						tairlineName.setText("");
						tSource.setText("");
						tdestination.setText("");
						tcapacity.setText("");
						tpassList.setText("");
						add.disableProperty().set(false);
						update.disableProperty().set(true);
					}
				}
			}catch (Exception e1) {
				wrong.setText("Error in serch Flight : " + e1.getMessage());
			}
		});

		HBox h7 = new HBox(25 ,add,update ,search,back);
		h7.setAlignment(Pos.CENTER);

		VBox v = new VBox(15,wrong, h1,h2,h3,h4,h5,h6);
		v.setAlignment(Pos.CENTER);

		VBox v2 = new VBox(30 , v,h7);
		v2.setAlignment(Pos.CENTER);
		pane.setCenter(v2);

		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void diplayPassenger(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Label welcome = new Label("Passenger Information");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		TableView<Passenger> table = new TableView<Passenger>();

		table.setEditable(false);

		TableColumn <Passenger, Integer>flightNumber = new TableColumn <Passenger, Integer>("Flight Number");
		flightNumber.setMinWidth(198);
		flightNumber.setCellValueFactory(  p -> new SimpleIntegerProperty(p.getValue().getFlightNumber()).asObject());
		flightNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, Integer>ticketNumber = new TableColumn <Passenger, Integer>("Ticket Number");
		ticketNumber.setMinWidth(155);
		ticketNumber.setCellValueFactory( p -> new SimpleIntegerProperty(p.getValue().getTicketNumber()).asObject());
		ticketNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>fullName = new TableColumn<Passenger, String>("Full Name");
		fullName.setMinWidth(230);
		fullName.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getFullName()));
		fullName.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>passportNumber = new TableColumn<Passenger, String>("Passport Number");
		passportNumber.setMinWidth(150);
		passportNumber.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getPassportNumber()));
		passportNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Passenger, String>nationality = new TableColumn<Passenger, String>("Nationality");
		nationality.setMinWidth(200);
		nationality.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getNationality()));
		nationality.setStyle("-fx-alignment: CENTER;");


		TableColumn <Passenger, String>birthdate = new TableColumn<Passenger, String>("Birth Date");
		birthdate.setMinWidth(198);
		birthdate.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getBirthdate()));
		birthdate.setStyle("-fx-alignment: CENTER;");


		Label flight = new Label("Flight Number");
		flight.setPadding(new Insets(7));
		TextField flightt = new TextField();
		IconedTextFieled(flight, flightt);
		HBox h1 = new HBox(flight,flightt);
		h1.setAlignment(Pos.CENTER);


		ObservableList<Passenger> data = FXCollections.observableArrayList();

		table.setItems(data);
		table.setMaxWidth(1150);
		table.setMinHeight(250);
		table.getColumns().addAll(flightNumber,ticketNumber,fullName,passportNumber,nationality,birthdate);

		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 100);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});


		ImageView p =  new ImageView(new Image("search (3).png"));
		p.setFitHeight(70);
		p.setFitWidth(70);
		Button serch = new Button("Serch passengers for a flight.",p);
		serch.setPrefSize(450, 100);
		icons(serch);
		butoonEffect(serch);
		serch.setEffect(new DropShadow());
		serch.setOnAction(e ->{
			if(pas != null) {
				pas.clear();
				table.getItems().clear();
			}
			try {
				pas = getPassergerOfFlight(Integer.parseInt(flightt.getText().trim()));				
			} catch (Exception e2) {
				wrong.setText("Error: Input wrong!");
			}
			if(pas != null && !pas.isEmpty()) {
				for (int i = 0; i < pas.size(); i++) {
					table.getItems().add(pas.get(i));
				}
				wrong.setText("");
			}
			else	
				wrong.setText("Error: The flight has no passenger yet!");
		});

		HBox butt = new HBox(30,serch,back);
		butt.setAlignment(Pos.CENTER);

		VBox v = new VBox(30,welcome,wrong , table ,h1 ,butt);
		v.setAlignment(Pos.CENTER);
		pane.setCenter(v);

		pane.setPadding(new Insets(30));
		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private ObservableList<Passenger> getPassergerOfFlight(Integer data) {
		Node<Flight> curr = flightList.getHead();
		if (curr == null)
			return null;
		for (  ; curr != null; curr = curr.getNext()) {
			if (curr.getData().getFlightNumber() ==  data){
				ObservableList<Passenger> p =  FXCollections.observableArrayList();
				for (Node<Passenger> x = curr.getData().getPassList().getHead() ; x != null ; x = x.getNext())
					p.add(x.getData());
				return p;
			}
		}
		return null;
	}

	private void displayFlightButton(Stage primaryStage) {		
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Label welcome = new Label("Flight's Information");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(15));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n");

		TableView<Flight> table = new TableView<Flight>();

		table.setEditable(false);

		TableColumn <Flight, Integer>flightNumber = new TableColumn <Flight, Integer>("Flight Number");
		flightNumber.setMinWidth(198);
		flightNumber.setCellValueFactory(  p -> new SimpleIntegerProperty(p.getValue().getFlightNumber()).asObject());
		flightNumber.setStyle("-fx-alignment: CENTER;");

		TableColumn <Flight, String>airlineName = new TableColumn <Flight, String>("Airline Name");
		airlineName.setMinWidth(200);
		airlineName.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getAirlineName()));
		airlineName.setStyle("-fx-alignment: CENTER;");

		TableColumn <Flight, String>source = new TableColumn<Flight, String>("Source");
		source.setMinWidth(230);
		source.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getSource()));
		source.setStyle("-fx-alignment: CENTER;");

		TableColumn <Flight, String>destination = new TableColumn<Flight, String>("Destination");
		destination.setMinWidth(150);
		destination.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getDestination()));
		destination.setStyle("-fx-alignment: CENTER;");

		TableColumn <Flight, Integer>capacity = new TableColumn<Flight, Integer>("capacity");
		capacity.setMinWidth(200);
		capacity.setCellValueFactory( p -> new SimpleIntegerProperty(p.getValue().getCapacity()).asObject());
		capacity.setStyle("-fx-alignment: CENTER;");


		TableColumn <Flight, String>passList = new TableColumn<Flight, String>("Passenger List");
		passList.setMinWidth(400);
		passList.setCellValueFactory( p -> new SimpleStringProperty(p.getValue().getPassList().traverseToString()));
		passList.setStyle("-fx-alignment: CENTER;");

		ObservableList<Flight> data = FXCollections.observableArrayList();

		table.setItems(data);
		table.setMaxWidth(1350);
		table.setMinHeight(444);
		table.getColumns().addAll(flightNumber,airlineName,source,destination,capacity,passList);

		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(70);
		b.setFitWidth(70);
		Button back = new Button("Back to menu",b);
		back.setPrefSize(280, 100);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});


		ImageView p =  new ImageView(new Image("surveillance.png"));
		p.setFitHeight(70);
		p.setFitWidth(70);
		Button serch = new Button("Display flight's.",p);
		serch.setPrefSize(280, 100);
		icons(serch);
		butoonEffect(serch);
		serch.setEffect(new DropShadow());
		serch.setOnAction(e ->{
			if(fli != null) {
				fli.clear();
				table.getItems().clear();
			}
			try {
				fli = getFlights();				
			} catch (Exception e2) {
				wrong.setText("Error: Input wrong!");
			}
			if(fli != null && !fli.isEmpty()) {
				passList.setMinWidth(1250);
				for (int i = 0; i < fli.size(); i++) {
					table.getItems().add(fli.get(i));
				}
				wrong.setText("");
			}
			else	
				wrong.setText("Error: There are no flight !");
		});

		HBox butt = new HBox(30,serch,back);
		butt.setAlignment(Pos.CENTER);

		VBox v = new VBox(30,welcome,wrong , table ,butt);
		v.setAlignment(Pos.CENTER);
		pane.setCenter(v);

		pane.setPadding(new Insets(30));
		Scene scene= new Scene(pane,1535,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	private ObservableList<Flight> getFlights() {
		ObservableList<Flight> f =  FXCollections.observableArrayList();
		Node<Flight> curr = flightList.getHead();
		if (curr == null)
			return null;
		else {
			for (  ; curr != null; curr = curr.getNext()) {
				f.add(curr.getData());
			}
			return f;
		}
	}

	private void readButton(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Label welcome = new Label("Read File");
		welcome.setStyle("-fx-font-size: 40;");
		welcome.setPadding(new Insets(5));
		welcome.setTextFill(Color.web("silver"));
		pane.setTop(welcome);
		pane.setAlignment(welcome, Pos.CENTER);

		Label wrong = new Label();
		wrong.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: #f2f3f4;\n");

		ImageView r =  new ImageView(new Image("traveler.png"));
		r.setFitHeight(140);
		r.setFitWidth(140);
		Button readPass = new Button("Read Passenger File",r);
		readPass.setPrefSize(450, 200);
		icons(readPass);
		butoonEffect(readPass);
		readPass.setEffect(new DropShadow());
		readPass.setOnAction(e ->{
			try {
				readPassenger(primaryStage);				
				wrong.setText("Read Passenger File succesful ");
			}catch (Exception c) {
				wrong.setText(c.getMessage() + "\nRead Passenger File not succesful ");
			}
		});

		ImageView d =  new ImageView(new Image("travel.png"));
		d.setFitHeight(140);
		d.setFitWidth(140);
		Button readFlight = new Button("Read Flight File",d);
		readFlight.setPrefSize(450, 200);
		icons(readFlight);
		butoonEffect(readFlight);
		readFlight.setEffect(new DropShadow());
		readFlight.setOnAction(e ->{
			try {
				readFlight(primaryStage);				
				wrong.setText("Read Flight File succesful ");
			}catch (Exception c) {
				wrong.setText(c.getMessage() + "\nRead Flight File not succesful ");
			}
		});

		HBox h = new HBox(40, readPass,readFlight);
		h.setAlignment(Pos.CENTER);

		ImageView b =  new ImageView(new Image("reply.png"));
		b.setFitHeight(140);
		b.setFitWidth(140);
		Button back = new Button("Back to \nmenu",b);
		back.setPrefSize(300, 150);
		icons(back);
		butoonEffect(back);
		back.setEffect(new DropShadow());
		back.setOnAction(e ->{
			menu(primaryStage);
		});

		VBox v = new VBox(50,wrong,h,back);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);
		pane.setPadding(new Insets(10));
		Scene scene= new Scene(pane,1535,800);
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	public static void main(String[] args) {
		launch(args);
	}

	private void readPassenger(Stage  primaryStage) throws Exception{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File("C:\\Users\\Anas Taleeb\\Desktop\\BZU\\Data Structer\\learn\\phase1_Anas"));
		File passengerInput = fileChooser.showOpenDialog(primaryStage);
		Scanner sc = new Scanner(passengerInput);
		if(passengerInput.exists()) {
			while(sc.hasNext()) {
				String tok = sc.nextLine();
				String [] split = tok.split(",");
				String [] tok1 =  split[5].split("/");
				Date date = new Date(Integer.parseInt(tok1[2])-1900, Integer.parseInt(tok1[1])-1, Integer.parseInt(tok1[0]));
				Passenger p = new Passenger(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], split[3], split[4], date);
				Node<Flight> curr = flightList.getHead();
				for (  ; curr != null ; curr = curr.getNext()) {
					if (curr.getData().getFlightNumber() == Integer.parseInt(split[0])) {
						curr.getData().getPassList().insert(p);
					}
				}
			}

		}
		sc.close();
	}

	private void readFlight(Stage  primaryStage) throws Exception{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File("C:\\Users\\Anas Taleeb\\Desktop\\BZU\\Data Structer\\learn\\phase1_Anas"));
		File flightInput = fileChooser.showOpenDialog(primaryStage);

		Scanner sc = new Scanner(flightInput);
		if(flightInput.exists()) {
			while(sc.hasNext()) {						
				String tok = sc.nextLine();
				String [] split = tok.split(",");
				Flight f = new Flight(Integer.parseInt(split[0].trim()), split[1], split[2], split[3], Integer.parseInt(split[4].trim()));
				flightList.insert(f);
			}

		}
		sc.close();
	}

	private void writePassengerFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("passengers.txt");
			Node<Flight> curr = flightList.getHead();
			for (  ; curr != null ; curr = curr.getNext()) {
				Node<Passenger> curr2 = curr.getData().getPassList().getHead();
				for (  ; curr2 != null ; curr2 = curr2.getNext()) {
					output.println(curr2.getData().getFlightNumber() + "," + curr2.getData().getTicketNumber() 
							+","+ curr2.getData().getFullName()+","+curr2.getData().getPassportNumber()+","
							+ curr2.getData().getNationality() +","+curr2.getData().getBirthdate());
				}
			}
			output.close();				
		} catch (FileNotFoundException e) {
			System.out.println("| "+ e.getMessage()+ " Error!! : File passengers.txt doesn't found. ");
		}
	}

	private void writeFlightFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("Flights.txt");
			Node<Flight> curr = flightList.getHead();
			for (  ; curr != null ; curr = curr.getNext()) {
				output.println(curr.getData().getFlightNumber()+","+curr.getData().getAirlineName()+
						","+curr.getData().getSource()+","+ curr.getData().getDestination()
						+","+curr.getData().getCapacity());
			}
			output.close();				
		} catch (FileNotFoundException e) {
			System.out.println("| "+ e.getMessage()+ " Error!! : File Flights.txt doesn't found. ");
		}
	}

	private void IconedTextFieled(javafx.scene.Node l, javafx.scene.Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 50;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" + 
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 50 50 0");
	}

	private void icons(javafx.scene.Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" +
				"-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 25 25 25 25");
	}

	private void butoonEffect(Button b) {
		b.setOnMouseMoved(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 20;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					"-fx-text-fill: #CE2029;\n"+
					"-fx-background-color: #d8d9e0;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 20;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					"-fx-text-fill: #f2f3f4;\n"+
					"-fx-background-color: transparent;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});

	}

}
