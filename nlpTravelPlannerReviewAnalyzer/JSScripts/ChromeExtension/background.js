chrome.browserAction.onClicked.addListener(function(tab) {
	chrome.tabs.getSelected(null, function(tab) {
		  chrome.tabs.sendMessage(tab.id, {message: "message"});
	});
});
    