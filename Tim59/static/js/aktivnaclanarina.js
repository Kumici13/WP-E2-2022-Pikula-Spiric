new Vue({
    el: '#aktivnaclanarina-app',
    data:   
	{
		aktivneClanarine: [],
        pretragaClanarine: '',
        tipTr: '',
        brTr: '',
        brUlazaka: '',
        cena: '',
        bodovi: '0',
      
      
    },
    mounted()  
	{

		let jwt = window.localStorage.getItem('jwt');
		
		axios
            .post('app/getClanarinaWithKorisnickoIme', jwt, 
				{     
                 headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')                  
                    }
                })
            .then(response => 
			{
                this.aktivneClanarine = response.data;


            })
            .catch(error => 
			{
                console.log(error);
				alert(error.response.data.sadrzaj);
            });
            
          axios
            .post('app/getBodoviWithKorisnickoIme', jwt, 
				{     
                 headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')                  
                    }
                })
            .then(response => 
			{
                this.bodovi = response.data;


            })
            .catch(error => 
			{
                console.log(error);
				alert(error.response.data.sadrzaj);
            });

              
          	

    },
   
   
    
	
});




