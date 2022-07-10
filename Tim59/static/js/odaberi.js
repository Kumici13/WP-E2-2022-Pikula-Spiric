new Vue({
    el: '#odaberi-app',
    data:   
	{
		addClanarina: [],
		odaberi: [],
		datumPlacanja: '',
		datumVazenja: '',
		tip: '',
		cena: '',
		brojTreninga:''    
	},
    
    mounted()   
	{
		    var x = window.localStorage.getItem('clanarina');
	        this.tip = x;
	
		    var d = new Date();
            this.date = d.toLocaleDateString("en-GB");  
			if(x == "Classic")
			{
				d.setDate(d.getDate() + 7);
				this.cena = 1500;
				this.brojTreninga = 4;
			}
			else if(x=="StudioClassic")
			{
				d.setDate(d.getDate() + 30);
				this.cena = 4500;
				this.brojTreninga = 15;
			}
			else if(x=="StudioElite")
			{
				d.setDate(d.getDate() + 365);
				this.cena = 50000;
				this.brojTreninga = 600;
			}
			else
			{
				alert("Morate odabrati clanarinu!");
            	window.location.href='clanarine.html';
			} 
           		this.datumVazenja = d.toLocaleDateString("en-GB");  
            this.datumPlacanja = this.date;
	},
   
    methods:    
	{
        ulogovanKorisnik: function()    
		{
            return window.localStorage.getItem('jwt') != null;
        },
     
     	AktivirajClanarinu: function()
     	{
		    var x = window.localStorage.getItem('clanarina');
			console.log(x);	
			
			axios
            .post('app/addClanarina' , x,
            {
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }

            })
            .then(response => 
			{
                this.addClanarina = response.data;
            	window.location.href='aktivnaclanarina.html';

            })
            .catch(error => 
			{
                console.log(error);
                
            });	

			}           
		}
		
});




