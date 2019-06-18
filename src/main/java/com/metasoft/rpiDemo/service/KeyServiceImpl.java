package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Key;
import com.metasoft.rpiDemo.repository.KeyRepository;
import com.metasoft.rpiDemo.system.SystemGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("keyService")
public class KeyServiceImpl implements KeyService{

    @Autowired
    private KeyRepository keyRepository;

    @Override
    public ApiResponse availableKey(Integer active) throws Exception {

        ApiResponse response = new ApiResponse(  );

        List<Key> keyExist = keyRepository.findAllByActive(active);
        if(keyExist!=null){
            response.setData( keyExist );
            response.setSuccessful( true );
            response.setMessageText( "Succeed" );
            return response;
        }
        else {

            response.setSuccessful( false );
            response.setMessageText( "Something's wrong" );
            return response;

        }

    }

    @Override
    public ApiResponse allKey() throws Exception {
        ApiResponse response = new ApiResponse(  );

        List<Key> keyExist = keyRepository.findAll();
        if(keyExist!=null){
            response.setData( keyExist );
            response.setSuccessful( true );
            response.setMessageText( "Succeed" );
            return response;
        }
        else {

            response.setSuccessful( false );
            response.setMessageText( "Something's wrong" );
            return response;

        }

    }

    @Override
    public ApiResponse addKey(Key key) {
        ApiResponse response = new ApiResponse();
        String keys = key.getKeyName();

        Key existingKey = keyRepository.findByKeyName(keys);
        if (existingKey != null) {
            if(existingKey.getActive()==0)
                existingKey.setActive( 1 );
            response.setSuccessful( true );
            response.setMessageText( "Key exists and Key is active" );
            return response;
        }

        Key beforeSaveKey = new Key();
        beforeSaveKey.setKeyName( key.getKeyName() );
        beforeSaveKey.setServerPort( key.getServerPort() );
        beforeSaveKey.setServerIp( key.getServerIp() );
        beforeSaveKey.setKeyIp( key.getKeyIp() );
        beforeSaveKey.setKeyInfo( key.getKeyInfo() );
        beforeSaveKey.setKeyAck( key.getKeyAck() );


        Key savedKey = keyRepository.save( beforeSaveKey );
        response.setSuccessful( true );
        response.setData( savedKey );
        response.setMessageText( "Key created" );
        return response;
    }

    @Override
    public ApiResponse updateKey(Key key) {
        ApiResponse response = new ApiResponse();
        Key existingKey = keyRepository.findById( key.getId() );

        if (existingKey !=null){

            if (key.getKeyName() != null)
                existingKey.setKeyName( key.getKeyName() );
            if (key.getKeyAck() != null)
                existingKey.setKeyAck(key.getKeyAck());
            if (key.getKeyInfo() != null)
                existingKey.setKeyInfo( key.getKeyInfo() );
            if ( key.getKeyIp() != null)
                existingKey.setKeyIp( key.getKeyIp() );
            if(key.getServerIp()!=null)
                existingKey.setServerIp(key.getServerIp());
            if(key.getServerPort()!=null)
                existingKey.setServerPort(key.getServerPort());

            Key savedKey = keyRepository.save( existingKey );
            response.setSuccessful( true );
            response.setData( savedKey );
            response.setMessageText( "Key is updated" );
            return response;
        } else {
            response.setSuccessful( false );
            response.setMessageText( "This Key not founded" );
            return response;
        }
    }

    @Override
    public ApiResponse deleteKey(Key key) {
        ApiResponse response =new ApiResponse( );
        keyRepository.findById( key.getId() );
        key.setActive( 0 );
        Key savedKey = keyRepository.save( key );
        response.setSuccessful( true );
        response.setData( savedKey);
        response.setMessageText( "Key deleted" );
        return response;

    }

}
