<!DOCTYPE html>

<html lang="no">
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="icon" href="bilder/coffeecup.png">
        <meta charset="utf-8" />
    </head>
<body>

<?PHP
//HTML kode for å sette opp input
//echo '<input type= "file" name="filbane" />';

if (empty($_FILES['filbane']['name']) ) {
	die ("<p>Ingen filer ble valgt</p>");
} else {
	//$temp_fil er midlertidig navn bestemt i php.ini
	$temp_fil = $_FILES['filbane']['tmp_name'];
	//Blir f.eks bilder/navn.jpg
	$filnavn = "uploads/" . $_FILES['filbane']['name'];
	//Må derfor kopiere fra denne over til et kjent sted/navn
	move_uploaded_file($temp_fil, $filnavn)
		or die ("En feil oppstod: Kunne ikke kopiere fil!");

	$filtype = $_FILES['filbane']['name']; //MIME-type til filen
	$storrelse = $_FILES['filbane']['size']; //Størrelsen
	//if (strstr($filtype, "jpg") ){ //Vis bildet
		echo "<img src='$filnavn' height='200' align='right' />";
		echo "<h3>Dette er bildet som ble lastet opp</h3>";
	//}//if
    
    include "menytilUpload.php"; 
	//echo "<tr><td class ='nr' id = ' filename'>http://ekeberg.it/android/".$filnavn." </td></tr>";
    //echo "<button name='Dish' id = http://ekeberg.it/android/".$filnavn." type='button' class='btn btn-primary savepicture-btn'>Lagre</button>";

	//Sender brukeren tilbake til backend
    //echo '<button id="tilbake" class="btn btn-primary savepicture-btn">ferdig</button>';

    //echo '<button id="tilbake" class="btn btn-primary onclick="goBack()">Go Back</button>';





	echo "\t<li>Type: $filtype</li>\n";
	echo "\t<li>Størrelse: $storrelse byte</li>\n";

	//Lager evt. en tekstfil med beskrivelsen
	if (!empty($_POST['beskrivelse']) ) {
		echo "\t<li>Beskrivelse: " . $_POST['beskrivelse'];
		echo "</li>\n";

		$tekstnavn = str_ireplace(".jpg", ".txt", $filnavn);
		$filpeker = fopen($tekstnavn, "w"); //bilder/navn.text
		fwrite($filpeker, $_POST['beskrivelse']);
		fclose($filpeker);
	}//behandle beskrivelse
	echo "\n</ul>";
}//else, ferdig med å kopiere og vise info om fil

echo "<button name='Dish' id = http://ekeberg.it/android/".$filnavn." type='button' class='btn btn-primary savepicture-btn'>Lagre</button>";

	//Sender brukeren tilbake til backend
    //echo '<button id="tilbake" class="btn btn-primary savepicture-btn">ferdig</button>';

    echo '<button id="tilbake" class="btn btn-primary onclick="goBack()">Tilbake</button>';
?>
     </body>
    <script src="editableTable.js"></script>



    <!--Sender brukeren tilbake til backend-->
   <script type="text/javascript">
    	document.getElementById("tilbake").onclick = function () {
        	//location.href = "http://localhost/backendv2/bildeInput.php";
        	window.history.back();
    	};
    </script>


    <!--<script>
		function goBack() {
    		window.history.back();
		}
	</script>-->
</html>