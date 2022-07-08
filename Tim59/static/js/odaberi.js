new Vue({
    el: '#odaberi-app',
    data:   
	{
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
      /*  
       sendToAktivneClanarine: function()
       {
		
		
		axios
            .post('app/sendToAktivneClanarine' , this.datumPlacanja, this.datumVazenja)
            .then(response => 
			{
                this.sendToAktivneClanarine = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
		}, */

       	
    },
 
	
});




