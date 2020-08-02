package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.awt.*;
import java.io.InputStream;
//
// mport java.lang.ref.Cleaner;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {
	private static final int row=6;
	private static final int column=7;
	private static final float circ=75f;
	private static String one="First Player";
	private static boolean allowed=true;
	private static String two="Second Player";
	private static String c1="#24303E";
	private static String c2="#4CAA88";
	private static boolean p1=true;
	private holes arr[][]=new holes[row][column];
	@FXML
	public GridPane rootnode;
	@FXML
	public Pane inserted;
	@FXML
	public TextField first;
	@FXML
	public TextField second;
	@FXML
	public Button setit;
	@FXML
	public Label player;
	public void playg()
	{
		//first.setText(one);
		//second.setText(two);
		first.setPromptText(one);
		first.setAccessibleText(one);
		second.setPromptText(two);
		setit.setOnAction(event -> {setname();});
		Shape n=done();
	rootnode.add(n,0,1);
	List<Rectangle>trep=angle();
	for(Rectangle lk:trep)
	{
	rootnode.add(lk,0,1);
	}}
				public Shape done()
		{
		Shape   n=new Rectangle((column+1)*circ,(row+1)*circ);

for(int r=0;r<row;r++){

for(int ij=0;ij<column;ij++){

		Circle ast=new Circle();
		ast.setCenterX(circ/2);
		ast.setCenterY(circ/2);
		ast.setRadius(circ/2);
	ast.setSmooth(true);
	ast.setTranslateX(ij*(circ+5)+(circ/4));
	ast.setTranslateY(r*(circ+5)+(circ/4));
	n=Shape.subtract(n,ast);
	}}//rootnode.add(n,0,1);
		n.setFill(Color.WHITE);
	return n;
	}
	public List<Rectangle> angle(){
		List again=new ArrayList<>();
		for (int h =0;h<column;h++){
	Rectangle rect=new Rectangle(circ,(row+1)*circ)	;
	rect.setFill(Color.TRANSPARENT);
	rect.setTranslateX(circ/4);
	rect.setTranslateX(h*(circ+5)+(circ/4));
	rect.setOnMouseEntered(event -> rect.setFill(Color.valueOf("#eeeeee26")));
	rect.setOnMouseClicked(event -> rect.setFill(Color.TRANSPARENT));
			final int fg=h;
	rect.setOnMouseClicked(event -> {
if(allowed) {allowed=false;
	create(new holes(p1), fg);
}
	});
		again.add(rect);}
	return again;
	}
private  void create(holes ho,int col)
{int rows=row-1;
while(rows>=0)
{
	if(discifpresent(rows,col)==null)//nullarr[rows][col]
		break;
	else
		rows--;}
if(rows<0) return;
	arr[rows][col]=ho;
	inserted.getChildren().add(ho);
	ho.setTranslateX(col*(circ+5)+(circ/4));
	int cur_row=rows;
//ho.setTranslateY(rows*(circ+5)+(circ/4));
	TranslateTransition fin=new TranslateTransition((Duration.seconds(0.2)),ho);

	fin.setToY(rows*(circ+5)+(circ));
fin.setOnFinished(event -> {
	allowed=true;
if(fin(cur_row,col))
	{
		ended();}


p1=!p1;
	player.setText((p1)?one:two);});
fin.play();
}

	private void ended() {
//System.out.print(1  );
		String asd=p1?one:two;
	//	System.out.println(asd);
		Alert hj=new Alert(Alert.AlertType.INFORMATION);
		ButtonType yes=new ButtonType("Yes");
		ButtonType no= new ButtonType("No");
	//	hj.showAndWait();
		hj.getButtonTypes().setAll(yes,no);
		//hj.showAndWait();
		//hj.show();
	hj.setTitle("Connect4");
	hj.setHeaderText(asd+" is the winner!!!\n Congratulations!!!!!!!!");
		hj.setContentText("Do you want to play again??");
	Platform.runLater(()->{
//
//		hj.setContentText(asd+" is the winner!!!\n Congratulations!!!!!!!!");
//		hj.setContentText("Do you want to play again??");
		Optional<ButtonType> option=hj.showAndWait();

		if(option.isPresent()&&option.get()==no)
		{Platform.exit();
			System.exit(0);}
		else
			resetGame();

	});



		//hj.show();
	}

	public void resetGame() {
	inserted.getChildren().clear();
	for(int g=0;g<arr.length;g++)
	{
		for (int k=0;k<arr[g].length;k++)
		{arr[g][k]=null;

		}
	}p1=true;
	player.setText(one);
	playg();
	}



	private boolean fin(int r,int columns) {
		//System.out.print(1);
		List<Point2D> df=IntStream.rangeClosed(r-3,r+3).mapToObj(rown->new Point2D(rown,columns)).collect(Collectors.toList());
		List<Point2D> cf=IntStream.rangeClosed(columns-3,columns+3).mapToObj(c->new Point2D(r,c)).collect(Collectors.toList());
		Point2D points=new Point2D(r-3,columns+3);
		List<Point2D> plz=IntStream.rangeClosed(0,row).mapToObj(ki->points.add(ki,-ki)).collect(Collectors.toList());
		Point2D points2=new Point2D(r-3,columns-3);
		List<Point2D> plz2=IntStream.rangeClosed(0,row).mapToObj(ki1->points2.add(ki1,ki1)).collect(Collectors.toList());
		boolean gets=combination(df)||combination(cf)||combination(plz)||combination(plz2);
		//System.out.print(gets);
		return gets;
	}

	private boolean combination(List<Point2D> point) {
	//	System.out.print(1);
		int chain=0;
		for (Point2D x:point ) {
			int rs= (int) x.getX();
			int cs=(int) x.getY();
			holes h=discifpresent(rs,cs);//arr[rs][cs];
			if(h!=null && h.p1t==p1)
			{ //  System.out.print(123);
				chain++;
				if(chain==4 ) {
					//System.out.print(chain);
					return true;}
				}
				else
					chain = 0;

		} return false;}


	private holes discifpresent(int rs1, int cs1) {
		if(rs1>=row||rs1<0||cs1>=column||cs1<0) return null;
		return arr[rs1][cs1];
	}

	private static class  holes extends Circle{
		private final boolean p1t;
		public  holes(boolean p1t)
		{
			this.p1t=p1t;
			setRadius(circ/2);
			setCenterX(circ/2);
			setCenterY(circ/10);
			setFill(p1t?Color.valueOf(c1):Color.valueOf(c2));
		}
	}

	public void setname()
	{
		one=first.getText();
		//System.out.print(f);
		two=second.getText();

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
