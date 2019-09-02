import React, {Component} from 'react';
import {render} from 'react-dom';
import AvatarUploader from 'react-avatar-uploader';
import NavBar from '../NavBar/NavBar';
import ImageUploader from 'react-images-upload';

const  App = () => (
  <AvatarUploader
   size={150}
   uploadURL="http://localhost:3000"
   fileType={"image/png"}/>
  );

class Profile extends Component {
 
  render() {
    return ( <>
      <NavBar/>
      <App/>
     </> )
    };
  }

  export default Profile;
      
