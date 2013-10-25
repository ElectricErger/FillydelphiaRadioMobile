jQuery(document).ready(function() {
    pollstation();
    //refresh the data every 30 seconds
    setInterval(pollstation, 30000);
});

// Accepts a url and a callback function to run.  
function requestCrossDomain( callback ) {  
    // Take the provided url, and add it to a YQL query. Make sure you encode it!  
    var yql = 'http://fillyradio.com/nowplayingAPI/yql.php?callback=?';
    // Request that YSQL string, and run a callback function.  
    // Pass a defined function to prevent cache-busting.  
    jQuery.getJSON( yql, cbFunc );
  
    function cbFunc(data) {  
    // If we have something to work with...  
    if ( data ) {  
        // Strip out all script tags, for security reasons. there shouldn't be any, however
        data = data[0].results.replace(/<script[^>]*>[\s\S]*?<\/script>/gi, '');
        data = data.replace(/<html[^>]*>/gi, '');
        data = data.replace(/<\/html>/gi, '');
        data = data.replace(/<body[^>]*>/gi, '');
        data = data.replace(/<\/body>/gi, '');
  
        // If the user passed a callback, and it  
        // is a function, call it, and send through the data var.  
        if ( typeof callback === 'function') {  
            callback(data);  
        }  
    }  
    // Else, Maybe we requested a site that doesn't exist, and nothing returned.  
    else throw new Error('Nothing returned from getJSON.');  
    }  
}  

function pollstation() {
    requestCrossDomain(function(stationdata) {
        //make our data into an array
        var lines = stationdata.split('<br/>');
        
        //update number of listeners
        jQuery('#listeners').html(lines[0]);           

        //transform the song title into [artist] - [title] ([year])
        s_info=lines[1].split(" - ");
        
        //remove the artist from the title
        title=jQuery.trim(s_info[1]);
        
        //remove the year from the title
        cleantitle=jQuery.trim(s_info[1].replace(/\ \(\d{4}\)/,''));
        
        //keep just the year
        new_year=title.replace(cleantitle, '');
        
        //get rid of parenthesis around the year
        new_year=new_year.replace(/\ \(/,'');
        new_year=new_year.replace(/\)/,'');
        
        //if a special show, let's identify it and properly format it
        var index = cleantitle.indexOf("[Fillydelphia Radio]");
        if(index != -1) {
            //remove the show title from the title of the song
            cleantitle=cleantitle.replace(/\ \[Fillydelphia Radio\]/,'');
            
            //replace the year with the song
            new_year='Fillydelphia Radio';
            
            //update the album art for the show
            jQuery('#songsearch').html('<img src="http://fillyradio.com/nowplayingAPI/station.jpg" alt="Fillydelphia Radio" />');
        }
        
        //update the current artist and song title
        jQuery('#currentsong').html('<span style="font-weight:bold;">' + jQuery.trim(cleantitle) + '</span><br /><span><em>' + jQuery.trim(s_info[0]) + '</em></span><br /><div style="font-weight:bold;font-size: 14px;">' + jQuery.trim(new_year) + '</div>');
        
        //update the previously played songs
        for (var i = 1; i <= 10; i++) {            
            jQuery('#prevsong' + i).html('<a href="http://fillyradio.com/redirect.php?song=' + encodeURIComponent(jQuery.trim(lines[i+1])) + '" title="view song information"  style="text-decoration:none;font-weight:normal" target="_blank">' + lines[i + 1] + '</a>');
        }
    } );
}