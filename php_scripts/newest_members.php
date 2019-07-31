<?php

    require_once 'connect.php';

    $sql = "SELECT * FROM users ORDER BY id DESC LIMIT 3";

    $response = mysqli_query($conn, $sql);
    $result = array();
    $result['top'] = array();
    
    if ( mysqli_num_rows($response) === 3 ) {
		
		while($row=mysqli_fetch_array($response)){
        
			//$row = mysqli_fetch_assoc($response);

			$index['id'] = $row['id'];
			$index['name'] = $row['name'];
			$index['surname'] = $row['surname'];
			$index['email'] = $row['email'];
			$index['phone'] = $row['phone'];
			$index['role'] = $row['role'];
			array_push($result['top'], $index);
		}
        $result['success'] = "1";
        $result['message'] = "success";
        echo json_encode($result);
		
        mysqli_close($conn);
	} else {

        $result['success'] = "0";
        $result['message'] = "error";
        echo json_encode($result);

        mysqli_close($conn);

    }

?>