new Vue({
    el: '#trener-trening-app',
    data:   
	{
       treninzi:[]
        
    },
    
    
     mounted()  
	{
		this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 3) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

		let jwt = window.localStorage.getItem('jwt');
        axios
            .post('app/getIstorijuTreningaByTrenerId', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.treninzi = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
    },
    
     methods:    
	{
		otkaziIstorijuTreninga: function(objekat)
		{
			let jwt = window.localStorage.getItem('jwt');
        	axios
            .post('app/otkaziTreningTrener', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'istorijaTreningaId': objekat.id
                    }
                })
            .then(response => 
			{
				alert(response.data);
				window.location.reload(true)
            })
            .catch(error => 
			{
                console.log(error);
                
				alert(response.data);
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
        },

		prikaziStart: function(objekat)
		{	
			var dateString = objekat.datumVreme; // Oct 23

			var dateParts = dateString.split("/");

			var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
			
			let day = dateObject.getDay();
				if(day == 0)
				{
					return objekat.trening.radnoVreme.nedeljaDan.danStart;
				}
				else if(day == 1)
				{
					return objekat.trening.radnoVreme.ponedeljakDan.danStart;
				}
				else if(day == 2)
				{
					return objekat.trening.radnoVreme.utorakDan.danStart;
				}
				else if(day == 3)
				{
					return objekat.trening.radnoVreme.sredaDan.danStart;
				}
				else if(day == 4)
				{
					return objekat.trening.radnoVreme.cetvrtakDan.danStart;
				}
				else if(day == 5)
				{
					return objekat.trening.radnoVreme.petakDan.danStart;
				}
				else if(day == 6)
				{
					return objekat.trening.radnoVreme.subotaDan.danStart;
				}		
		},
		prikaziKraj: function(objekat)
		{ 
			var dateString = objekat.datumVreme; // Oct 23

			var dateParts = dateString.split("/");

			var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
			
			let day = dateObject.getDay();
			
				if(day == 0)
				{
					return objekat.trening.radnoVreme.nedeljaDan.danEnd;
				}
				else if(day == 1)
				{
					return objekat.trening.radnoVreme.ponedeljakDan.danEnd;
				}
				else if(day == 2)
				{
					return objekat.trening.radnoVreme.utorakDan.danEnd;
				}
				else if(day == 3)
				{
					return objekat.trening.radnoVreme.sredaDan.danEnd;
				}
				else if(day == 4)
				{
					return objekat.trening.radnoVreme.cetvrtakDan.danEnd;
				}
				else if(day == 5)
				{
					return objekat.trening.radnoVreme.petakDan.danEnd;
				}
				else if(day == 6)
				{
					return objekat.trening.radnoVreme.subotaDan.danEnd;
				}
		},
		
		mozeDaOtkaze: function(objekat)
		{
			if(objekat.trening.tip != 'Personalni')
			{
				return false;
			}
			
			let now = new Date();
			
  			now.setDate(now.getDate() + 2);
			var dateString = objekat.datumVreme; // Oct 23

			var dateParts = dateString.split("/");

			var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
			console.log(dateObject);
			console.log(now);
			if(dateObject < now)
			{
				return false;
			}
			return true;
		}
        
    }
});




