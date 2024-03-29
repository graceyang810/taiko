function rand(min,max){
return Math.round(min+(Math.random()*(max-min)));
};

var fade = function(element, transparency, speed, callback){//透明度渐变：transparency:透明度 0(全透)-100(不透)；speed:速度1-100，默认为1
	if(typeof(element)=='string')element=document.getElementById(element);
	if(!element.effect){
		element.effect = {};
		element.effect.fade=0;
	}
	clearInterval(element.effect.fade);
	var speed=speed||1;
	var start=(function(elem){
		var alpha;
		if(navigator.userAgent.toLowerCase().indexOf('msie') != -1){
				alpha=elem.currentStyle.filter.indexOf("opacity=") >= 0?(parseFloat( elem.currentStyle.filter.match(/opacity=([^)]*)/)[1] )) + '':
				'100';
		}else{
				alpha=100*elem.ownerDocument.defaultView.getComputedStyle(elem,null)['opacity'];
		}
		return alpha;
	})(element);
	if(window.console&&window.console.log)console.log('start: '+start+" end: "+transparency);
	element.effect.fade = setInterval(function(){
		start = start < transparency ? Math.min(start + speed, transparency) : Math.max(start - speed, transparency);
		element.style.opacity =  start / 100;
		element.style.filter = 'alpha(opacity=' + start + ')';
		if(Math.round(start) == transparency){
			element.style.opacity =  transparency / 100;
			element.style.filter = 'alpha(opacity=' + transparency + ')';
			clearInterval(element.effect.fade);
			if(callback)callback.call(element);
		}
	}, 20);
};

var move = function(element, position, speed, callback){//移动到指定位置，position:移动到指定left及top 格式{left:120, top:340}或{left:120}或{top:340}；speed:速度 1-100，默认为10
	if(typeof(element)=='string')element=document.getElementById(element);
	if(!element.effect){
		element.effect = {};
		element.effect.move=0;
	}
	clearInterval(element.effect.move);
	var speed=speed||10;
	var start=(function(elem){
		var	posi = {left:elem.offsetLeft, top:elem.offsetTop};
		while(elem = elem.offsetParent){
			posi.left += elem.offsetLeft;
			posi.top += elem.offsetTop;
		};
		return posi;
	})(element);
	element.style.position = 'absolute';
	var	style = element.style;
	var styleArr=[];
	if(typeof(position.left)=='number')styleArr.push('left');
	if(typeof(position.top)=='number')styleArr.push('top');
	element.effect.move = setInterval(function(){
		for(var i=0;i<styleArr.length;i++){
			start[styleArr[i]] += (position[styleArr[i]] - start[styleArr[i]]) * speed/100;
			style[styleArr[i]] = start[styleArr[i]] + 'px';
		}
		for(var i=0;i<styleArr.length;i++){
			if(Math.round(start[styleArr[i]]) == position[styleArr[i]]){
				if(i!=styleArr.length-1)continue;
			}else{
				break;
			}
			for(var i=0;i<styleArr.length;i++)style[styleArr[i]] = position[styleArr[i]] + 'px';
			clearInterval(element.effect.move);
			if(callback)callback.call(element);
		}
	}, 20);
};
var resize = function(element, size, speed, callback){//长宽渐变：size:要改变到的尺寸 格式 {width:400, height:250}或{width:400}或{height:250}；speed:速度 1-100，默认为10
	if(typeof(element)=='string')element=document.getElementById(element);
	if(!element.effect){
		element.effect = {};
		element.effect.resize=0;
	}
	clearInterval(element.effect.resize);
	var speed=speed||10;
	var	start = {width:element.offsetWidth, height:element.offsetHeight};
	var styleArr=[];
	if(!(navigator.userAgent.toLowerCase().indexOf('msie') != -1&&document.compatMode == 'BackCompat')){
		//除了ie下border-content式盒模型情况外，需要对size加以修正
		var CStyle=document.defaultView?document.defaultView.getComputedStyle(element,null):element.currentStyle;
		if(typeof(size.width)=='number'){
			styleArr.push('width');
			size.width=size.width-CStyle.paddingLeft.replace(/\D/g,'')-CStyle.paddingRight.replace(/\D/g,'');
		}
		if(typeof(size.height)=='number'){
			styleArr.push('height');
			size.height=size.height-CStyle.paddingTop.replace(/\D/g,'')-CStyle.paddingBottom.replace(/\D/g,'');
		}
	}
	element.style.overflow = 'hidden';
	var	style = element.style;
	element.effect.resize = setInterval(function(){
		for(var i=0;i<styleArr.length;i++){
			start[styleArr[i]] += (size[styleArr[i]] - start[styleArr[i]]) * speed/100;
			style[styleArr[i]] = start[styleArr[i]] + 'px';
		}
		for(var i=0;i<styleArr.length;i++){
			if(Math.round(start[styleArr[i]]) == size[styleArr[i]]){
				if(i!=styleArr.length-1)continue;
			}else{
				break;
			}
			for(var i=0;i<styleArr.length;i++)style[styleArr[i]] = size[styleArr[i]] + 'px';
			clearInterval(element.effect.resize);
			if(callback)callback.call(element);
		}
	}, 20);
};