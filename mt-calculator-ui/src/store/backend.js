export const requestToken = (url, loginInfo) => {

    return fetch(url, {
       method: 'post',
       headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
       body: JSON.stringify(loginInfo)
    })
    .then(response => {
        if (!response.ok ) {
            throw new Error(response.statusText);
        }
        return response.json();
    });
} 

export const sendTrainingResults = (url, results) => {
    const token = loadToken();

    return fetch(url, {
       method: 'post',
       headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      },
       body: JSON.stringify(results)
    })
    .then(response => response.json());
} 

export const loadToken = () => {

    const tokenData = JSON.parse(localStorage.getItem('mtcalctoken'))
    if (tokenData && tokenData.access_token && tokenData.token_expire) {
        const currentDate = new Date()
        const expireDate = new Date(tokenData.token_expire);
        const token = (currentDate < expireDate) && tokenData.access_token;
        return token;
    }
    return null;
}

export const requestData = (url) => {

    const token = loadToken();

    var f;
    if (token) {
        f = fetch(url, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + token
            })
        })
    } else {
        f = fetch(url)
    }

    return f.then(response => {
        if (!response.ok ) {
            
            throw new Error(response.statusText);
        }
        return response.json();
    })
} 