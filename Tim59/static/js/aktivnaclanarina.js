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
      
      
    },
    mounted()  
	{
	
        axios
        	
            post('app/getClanarinaWithKorisnickoIme' , window.localStorage.getItem('jwt'),
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
          	
    },
   
   
    
	
});




