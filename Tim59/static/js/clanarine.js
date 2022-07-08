new Vue({
    el: '#clanarine-app',
    data:   
	{
		clanarine: [],
		//changeActivityOfClanarine: [],
        pretragaClanarine: '',
      
      
    },
    mounted()  
	{
        axios
            .get('app/getclanarine')
            .then(response => 
			{
                this.clanarine = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    methods:    
	{
        ulogovanKorisnik: function()    
		{
            return window.localStorage.getItem('jwt') != null;
        },
        
     /*  sendToAktivneClanarine: function(regKorisnik)
       {
		this.korisnickoIme =  regKorisnik 
		
		axios
            .post('app/changeActivityOfClanarine' , this.korisnickoIme )
            .then(response => 
			{
                this.changeActivityOfUser = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
	
	   } */
       	
    },
    computed:   
	{
        clanarineFilter: function() 
		{
            return this.clanarine.filter((objekat) => 
			{	
                return 	   ((objekat.datumPlacanja.toLowerCase().match(this.pretragaClanarine.toLowerCase()))
						 )
											
				});
        }
    }
	
});




