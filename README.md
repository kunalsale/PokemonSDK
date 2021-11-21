# PokemonSDK

Pokemon SDK provides APIs to search for a Pokemon. Client needs to call the necessary classes and method to get the pokemon info. All the logic is written inside the SDK.

## How to integrate into app:

1. Add the below code in the `setting.gradle` of the top level
  - ```
    maven { url 'https://jitpack.io' }
    ```

2. Add the below code in the `build.gradle`
  - ```
    implementation 'com.github.kunalsale:PokemonSDK:1.0.5'
    ```
## Built with:
1. Kotlin
2. Coroutines
3. Flow
4. Retrofit
5. Moshi
6. Hilt
7. Compose
8. Clean Architecture

## Modules:

PokemonSDK
  - `app`: Sample app to test the sdk 
  - `pokemon_sdk`:
      - `api`: Contains all the classes for Retrofit and responses of the APIs
      - `data`: Contains Repository classes and their implementation
      - `di`: Dependency injection module 
      - `ui`: All the UI components like screens are here
      - `usecase`: Usecase classes to be integrated by the client app
      
## Architecture
  - There are Retrofit services which are interacting with the API
  - Repositories are connecting with the Services. 
    - There are interfaces for the Repo and their implementation
  - Usecase class will interact with the client to provide the service.
  
   ![This is an image](https://user-images.githubusercontent.com/31345204/142767090-7d3063d8-ba25-4915-9569-30fdce815db6.png)
   
### Security:
   - Used `internal` for the Impl classes so they are not visible into the Client.
   - Used `Sealed` classes for the communication and it cannot be inheritated in the Client.
