package ee.nx01.tonclient.abi

import ee.nx01.tonclient.TonClient

/**
    # Module abi

    Provides message encoding and decoding according to the ABI specification.
 */
class AbiModule(private val tonClient: TonClient) {
    /**
    ## encode_message

    Encodes an ABI-compatible message

    Allows to encode deploy and function call messages,
    both signed and unsigned.

    Use cases include messages of any possible type:
    - deploy with initial function call (i.e. `constructor` or any other function that is used for some kind
    of initialization);
    - deploy without initial function call;
    - signed/unsigned + data for signing.

    `Signer` defines how the message should or shouldn't be signed:

    `Signer::None` creates an unsigned message. This may be needed in case of some public methods,
    that do not require authorization by pubkey.

    `Signer::External` takes public key and returns `data_to_sign` for later signing.
    Use `attach_signature` method with the result signature to get the signed message.

    `Signer::Keys` creates a signed message with provided key pair.

    [SOON] `Signer::SigningBox` Allows using a special interface to imlepement signing
    without private key disclosure to SDK. For instance, in case of using a cold wallet or HSM,
    when application calls some API to sign data.
     */
    suspend fun encodeMessage(params: ParamsOfEncodeMessage): ResultOfEncodeMessage {
        return tonClient.request("abi.encode_message", params)
    }

    suspend fun encodeMessageBody(params: ParamsOfEncodeMessageBody): ResultOfEncodeMessageBody {
        return tonClient.request("abi.encode_message_body", params)
    }

    suspend fun decodeMessage(params: ParamsOfDecodeMessage): DecodedMessageBody {
        return tonClient.request("abi.decode_message", params)
    }

    /**
    ## encode_account

    Creates account state BOC

    Creates account state provided with one of these sets of data :
    1. BOC of code, BOC of data, BOC of library
    2. TVC (string in `base64`), keys, init params
     */
    suspend fun encodeAccount(params: ParamsOfEncodeAccount) {
        return tonClient.request("abi.encode_account", params)
    }

    /**
    ## attach_signature

    Combines `hex`-encoded `signature` with `base64`-encoded `unsigned_message`. Returns signed message encoded in `base64`.
     */
    suspend fun attachSignatureToMessageBody(params: ParamsOfAttachSignatureToMessageBody): ResultOfAttachSignatureToMessageBody {
        return tonClient.request("abi.attach_signature_to_message_body", params)
    }
}


