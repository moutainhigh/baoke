function justifyHScroll(gridId, pagerId, extNum) {
	var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1
	if (isChrome) {
		var grid = $("#gbox_" + gridId), pager = $("#" + pagerId);
		var head = grid.find(".ui-corner-top").eq("0"), title = grid.find(
				".ui-jqgrid-hdiv").eq("0"), body = grid.find(".ui-jqgrid-bdiv")
				.eq("0");
		var pw = grid.width();

		grid.css("width", (pw + extNum) + "px");
		head.css("width", (pw + extNum - 5) + "px");
		title.css("width", (pw + extNum) + "px");
		body.css("width", (pw + extNum) + "px");
		pager.css("width", (pw + extNum) + "px");
	}
}
