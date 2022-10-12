	 function limitDate() { // 시작일자 선택 시 종료일자 컨트롤 활성화
	      var sdate = document.getElementById('sdate');                  	//시작일 컨트롤
	      var edate = document.getElementById('edate');                   	//종료일 컨트롤
	      var strDate = sdate.value;                                 		//시작일 컨트롤의 value
	      var arr1 = strDate.split('-');                              		//시작일 컨트롤의 value값을  배열로
	      var dat1 = new Date(arr1[0], arr1[1] - 1, arr1[2]);               //시작일 컨트롤의 date(일)값 (계산을 위해 필요함)
	      
	      
	      edate.setAttribute("min", strDate);      							//min값 설정
	      var dat2 = new Date(dat1.setDate(dat1.getDate()+9));            	//시작일에서 9일 더한 날짜 Date형 인스턴스 dat2에 저장
	      edate.removeAttribute('disabled');                           		//edate 'disabled' 속성 삭제
	      
	      var fullEDate = dat2.toLocaleString().substring(0,12).replaceAll(". ","-").replace(".","");   
	      
	      																	//종료일 스트링형으로 변환
	      var date2 = fullEDate.substring(8);                           	//종료일의 date(일)값
	      if ( date2 < 10 ) { date2 = "0" + date2; }                    	//date(일) 값이 10보다 작으면 앞에 0붙이기 
	      
	       edate.setAttribute("max", fullEDate.substring(0,8) + date2);     //max값 설정
	       setDay(sdate.value, edate.value, this.form.schedule_day);
	   }

	
	
	function setDay(sdate, edate, target) {	// 선택한 날짜를 계산하여 day(일차) select의 option을 변경
		
		var strDate1 = sdate;
		var strDate2 = edate;
		var arr1 = strDate1.split('-');
		var arr2 = strDate2.split('-');
		var dat1 = new Date(arr1[0], arr1[1] - 1, arr1[2]);
		var dat2 = new Date(arr2[0], arr2[1] - 1, arr2[2]);
		var diffDay = (dat2 - dat1) / ( 24 * 60 * 60 * 1000);
		
		
		for (var i = target.options.length -1; i > 0; i--) {
			target.options[i] = null;
		}	// 기존 day 삭제
		
		if ( sdate != "" && edate != "" ) {
			for ( var i = 1; i < diffDay + 2; i++) {
				target.options[i] = new Option("day" + i, i);
			}
			target.options[0].selected = true;
		}
	}
	
	function getDate(day, sdate) {
	      
		var arr1 = sdate.split('-');               
		var dat1 = new Date(arr1[0], arr1[1] - 1, arr1[2]);
		var dat2 = new Date(dat1.setDate((dat1.getDate()) + (day - 1)));
		  
		var fullEDate = dat2.toLocaleString().substring(0,12).replaceAll(". ","-").replace(".","");
		var date = fullEDate.substring(8);
		if ( date < 10 ) { date = "0" + date; }
		  
		return fullEDate.substring(0,8) + date;
	}

	