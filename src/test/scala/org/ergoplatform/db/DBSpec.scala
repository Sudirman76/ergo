package org.ergoplatform.db

import akka.util.ByteString
import org.iq80.leveldb.impl.Iq80DBFactory.bytes
import org.iq80.leveldb.{DB, Options}
import org.scalatest.{Matchers, PropSpec}
import scorex.testkit.utils.FileUtils

trait DBSpec extends PropSpec with Matchers with FileUtils {

  import LDBFactory.factory

  protected def byteString(s: String) = ByteString(bytes(s))

  protected def withDb(body: DB => Unit): Unit = {
    val options = new Options()
    options.createIfMissing(true)
    val db = factory.open(createTempDir, options)
    try body(db) finally db.close()
  }

}