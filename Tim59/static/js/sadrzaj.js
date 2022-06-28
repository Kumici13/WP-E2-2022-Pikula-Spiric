new Vue({
    el: '#sadrzaj-app',
    data:   
	{
        Sadrzaji: [],
        
    },
    mounted()  
	{
        axios
            .get('app/getSadrzaji')
            .then(response => 
			{
                this.Sadrzaji = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    	
});




