    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        if ($("#menu-toggle").text()== "Show Menu")
        	$("#menu-toggle").text("Hide Menu");
        else
        	$("#menu-toggle").text("Show Menu");
    });