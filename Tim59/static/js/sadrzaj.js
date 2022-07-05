new Vue({
    el: '#sadrzaj-app',
    data:   
	{
        getSadrzaji: [],
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
            
        axios
            .get('app/getSadrzaji')
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
    },
    
   
    
    computed:   
	{
        
		 sadrzajFilter: function() 
				{
		            return this.getSadrzaji.filter((objekat) => 
					{	
		                return 	   ((objekat.naziv.toLowerCase().match(this.pretragaCena.toLowerCase())) 
		                		) 					
						});
		        },
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




