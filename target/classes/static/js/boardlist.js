function moveAction(where) {
	switch (where) {
		case 1:
			location.href = "board/write";
			break;

		case 2:
			location.href = "board/list";
			break;
	}
}

$(function() {
	moveAction(where);
});