package com.nilo.mq;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

/**
 * Created by admin on 2017/10/19.
 */
public class KryoMarshaller {
    private Kryo kryo = new Kryo();
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public KryoMarshaller() {
    }

    public byte[] marshal(Object obj) {
        byte[] var3;
        try {
            Output out = new Output(this.outputStream);
            this.kryo.writeClassAndObject(out, obj);
            out.close();
            var3 = this.outputStream.toByteArray();
        } finally {
            this.outputStream.reset();
        }

        return var3;
    }

    public Object unmarshal(byte[] bytes) {
        Input in = new Input(bytes);
        return this.kryo.readClassAndObject(in);
    }
}
