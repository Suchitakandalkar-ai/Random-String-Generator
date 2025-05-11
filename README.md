**Random String Generator App**

- An Android app that queries a **Random String** from a custom Android **Content Provider** provided by **IAV**.
- The app allows users to set the maximum length for the string, retrieves a randomly generated string, displays its metadata, and stores history locally.


**Features**

- User sets the **Maximum length** of the random string
- Queries the **IAV Content Provider** for a new random string
- Displays:
  - The **Random String**
  - The **Length**
  - The **Timestamp** of generation
- Maintains a **History** of previously generated strings
- Allows deletion of:
  - A **Single String**
  - **All Strings**
- Full **Error handling** for slow or unreliable provider

**Content Provider Integration**

- **Authority**: `com.iav.contestdataprovider`
- **URI**: `content://com.iav.contestdataprovider/text`
- **Query Param**: `ContentResolver.QUERY_ARG_LIMIT`
- **Data Column**: `data` (contains a JSON string)

**Local Storage**
All fetched strings are stored locally (in a Room database) to preserve history and support deletion operations.

**Error Handling**

- Handles null/malformed responses
- Handles slow/unreliable queries via coroutines or background threads
- UI shows appropriate error messages and fallback behavior

 **UI Overview**

 - Input field for max length
 - "Generate" button
 - List of previous strings with:
   String value
   Length
   Timestamp
 - Delete buttons:
   For each string
   For all strings

 **Tech Stack**
 
 - Language: Kotlin
 - UI: XML
 - Storage: Room (for local persistence)
 - Architecture: MVVM
 - Permissions: Custom content provider access
 - Co routines and Live data
 - View Binding
 - Animation
 - Recyclerview




![Random String Generator](https://github.com/user-attachments/assets/b180b22c-ab0d-41ce-bcb3-94e00b678e3d)

