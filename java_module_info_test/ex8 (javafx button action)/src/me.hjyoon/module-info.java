module me.hjyoon {
	exports me.hjyoon;
	//requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.graphics;
    opens me.hjyoon to javafx.fxml;
}