function log(text) {
	if(console && console.log) {
		console.log(text);
	}
}

function QrWebSocket(url,channel,token) {
	that = this;
	this.wsUrl = url + '?channel=' + channel + '&token=' + token;
	this.ws = null;
	this.topThreeTime = 3;
	this.allowReconnect = true;
	this.afterThreeTime = 30;
	this.tryTimes = 0;
	this.send = function(message) {
		that.ws.send(message); 
	};
	this.connect = function() {
		log('websocket start connect...');
		that.ws = new WebSocket(that.wsUrl);
		that.ws.onopen = that.beforeConnected;
		that.ws.onclose = that.beforeDisconnected;
		that.ws.onmessage = that.beforeMessage;
		that.ws.onerror = that.onError;
	};
	this.beforeConnected = function(event) {
		log('websocket connect success.');
		that.tryTimes = 0;
		that.allowReconnect = true;
		that.onConnected(event);
	};
	this.onConnected = function(event) {
		
	};
	this.beforeDisconnected = function(event) {
		log('websocket disconnect.');
		if(that.allowReconnect) {
			that.reconnect();
		}
		that.onDisconnected(event);
	};
	this.onDisconnected = function(event) {
		
	};
	this.close = function() {
		log('connection close.');
		that.ws.close();
	};
	this.beforeMessage = function(event) {
		
		var msg = event.data;
		log('receive message:' + event.data);
		var obj = $.parseJSON(msg);
		
		if(obj.type == 0) {
			that.allowReconnect = false;
			that.ws.close();
			that.onT();
		}else {
			that.onMessage(obj.playload);
		}
	};
	this.onT = function() {
	
	};
	this.onMessage = function(msg) {
		
	};
	this.onError = function(event) {
		log('websocket error');
		log(event);
	};
	this.reconnect = function() {
		that.tryTimes ++;
		log('prepare for '+ that.tryTimes +' times to reconnect');
		if(that.tryTimes <= 3) {
			setTimeout('that.connect()',that.topThreeTime * 1000);
		}else {
			setTimeout('that.connect()',that.afterThreeTime * 1000);
		}
	};
}