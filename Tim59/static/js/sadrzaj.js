new Vue({
    el: '#sadrzaj-app',
    data:   
	{
        Sadrzaji: [],
        getTreneri: [],
        pretragaCena: '',
        
    },
    
    
     mounted()  
	{
        axios
            .get('app/getTreneri')
            .then(response => 
			{
                this.getTreneri = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
    },
    computed:   
	{
        
        regTreneriFilter: function() 
		{
            return this.getTreneri.filter((objekat) => 
			{	
                return 	   ((objekat.sifra.toLowerCase().match(this.pretragaCena.toLowerCase()))
						 )
											
				});
        }
       
    }
   
    	
});




