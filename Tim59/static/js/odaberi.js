new Vue({
    el: '#odaberi-app',
    data:   
	{
		addClanarina: [],
		odaberi: [],
		datumPlacanja: '',
		datumVazenja: '',  
		date: '',
		monthValidityDate: '',
		yearValidityDate: '',
          
    },
    
    mounted()   
	{
		    var d = new Date();
            this.date = d.toLocaleDateString("en-GB");  
            var dM = new Date(d.setMonth(d.getMonth() + 1));  
            d = new Date(); 
            var dY = new Date(d.setFullYear(d.getFullYear() + 1)); 
            this.monthValidityDate = dM.toLocaleDateString("en-GB");   
            this.yearValidityDate = dY.toLocaleDateString("en-GB");
            
            this.datumPlacanja = this.date;
            this.datumVazenja = this.monthValidityDate;
           
            
           
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




