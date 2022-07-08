new Vue({
    el: '#sadrzaj-app',
    data:   
	{
        getSadrzaji: '',
        getTreneri: [],
        pretragaCena: '',
		treningId: ''
        
    },
    
    
     mounted()  
	{
		this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 1) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

		let jwt = window.localStorage.getItem('jwt');
        axios
            .post('app/gettrenerizaobjekat', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.getTreneri = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
            
        axios
            .post('app/getTreningeZaObjekat', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.getSadrzaji = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    
     methods:    
	{
        izmeniSadrzajTreninga: function(trening)
       {
		
	   		this.trening = trening 
			console.log(trening);
	
	   },
	
	   obrisiTrening: function(trening)
       {
		let jwt = window.localStorage.getItem('jwt');
			console.log(trening);
			axios
            .post('app/obrisiTreningById', jwt,
				{
					
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						
                        'TreningId': trening,
                    }
                })
            .then(response => 
			{
                location.reload();
 				alert(response.data);
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
	
	   },
	   prikaziSliku: function(slika)
		{
            if (slika != null)  
			{
                return "data:image/jpeg;base64," + slika;
            } 
			else  
			{
                return '';
            }
        }
    }
});




