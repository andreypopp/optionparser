package ru.braintrace.optionparser

import org.specs._

object optionParserSpec extends Specification {

  "Option parser" should {

    "parse single short option" in {
      val parser = new OptionParser
      parser.addOption("o")
      val (options, args) = parser.parseArgs("-o"::"val"::Nil)
      options.size must_== 1
      options.get("o") must_== Some("val")
      args.size must_== 0
    }

    "parse many short options" in {
      val parser = new OptionParser
      parser.addOption("o")
      parser.addOption("k")
      val (options, args) = parser.parseArgs("-o"::"val1"::"-k"::"val2"::Nil)
      options.size must_== 2
      options.get("o") must_== Some("val1")
      options.get("k") must_== Some("val2")
      args.size must_== 0
    }

    "parse many short options and args" in {
      val parser = new OptionParser
      parser.addOption("o")
      parser.addOption("k")
      val (options, args) = parser.parseArgs(
        "-o"::"val1"::"-k"::"val2"::"arg1"::Nil)
      options.size must_== 2
      options.get("o") must_== Some("val1")
      options.get("k") must_== Some("val2")
      args.size must_== 1
      args(0) must_== "arg1"
    }

    "parse single flag short option" in {
      val parser = new OptionParser
      parser.addFlagOption("o")
      val (options, args) = parser.parseArgs("-o"::Nil)
      options.size must_== 1
      options.get("o").isDefined must_== true
      args.size must_== 0
    }

    "parse many flag short options" in {
      val parser = new OptionParser
      parser.addFlagOption("o")
      parser.addFlagOption("k")
      val (options, args) = parser.parseArgs("-o"::"-k"::Nil)
      options.size must_== 2
      options.get("o").isDefined must_== true
      options.get("k").isDefined must_== true
      args.size must_== 0
    }

    "parse many flag short options and args" in {
      val parser = new OptionParser
      parser.addFlagOption("o")
      parser.addFlagOption("k")
      val (options, args) = parser.parseArgs(
        "-o"::"-k"::"arg1"::Nil)
      options.size must_== 2
      options.get("o").isDefined must_== true
      options.get("k").isDefined must_== true
      args.size must_== 1
      args(0) must_== "arg1"
    }

    "parse single long option" in {
      val parser = new OptionParser
      parser.addOption("o", Some("opt"))
      val (options, args) = parser.parseArgs("--opt"::"val"::Nil)
      options.size must_== 2
      options.get("o") must_== Some("val")
      options.get("opt") must_== Some("val")
      args.size must_== 0
    }

    "parse many long options" in {
      val parser = new OptionParser
      parser.addOption("o", Some("opt"))
      parser.addOption("k", Some("kopt"))
      val (options, args) = parser.parseArgs(
        "--opt"::"val1"::"--kopt"::"val2"::Nil)
      options.size must_== 4
      options.get("o") must_== Some("val1")
      options.get("k") must_== Some("val2")
      options.get("opt") must_== Some("val1")
      options.get("kopt") must_== Some("val2")
      args.size must_== 0
    }

    "parse many long options and args" in {
      val parser = new OptionParser
      parser.addOption("o", Some("opt"))
      parser.addOption("k", Some("kopt"))
      val (options, args) = parser.parseArgs(
        "--opt"::"val1"::"--kopt"::"val2"::"arg1"::Nil)
      options.size must_== 4
      options.get("o") must_== Some("val1")
      options.get("k") must_== Some("val2")
      options.get("opt") must_== Some("val1")
      options.get("kopt") must_== Some("val2")
      args.size must_== 1
      args(0) must_== "arg1"
    }

    "parse single flag long option" in {
      val parser = new OptionParser
      parser.addFlagOption("o", Some("opt"))
      val (options, args) = parser.parseArgs("--opt"::Nil)
      options.size must_== 2
      options.get("o").isDefined must_== true
      options.get("opt").isDefined must_== true
      args.size must_== 0
    }

    "parse many flag long options" in {
      val parser = new OptionParser
      parser.addFlagOption("o", Some("opt"))
      parser.addFlagOption("k", Some("kopt"))
      val (options, args) = parser.parseArgs(
        "--opt"::"--kopt"::Nil)
      options.size must_== 4
      options.get("o").isDefined must_== true
      options.get("k").isDefined must_== true
      options.get("opt").isDefined must_== true
      options.get("kopt").isDefined must_== true
      args.size must_== 0
    }

    "parse many flag long options and args" in {
      val parser = new OptionParser
      parser.addFlagOption("o", Some("opt"))
      parser.addFlagOption("k", Some("kopt"))
      val (options, args) = parser.parseArgs(
        "--opt"::"--kopt"::"arg1"::Nil)
      options.size must_== 4
      options.get("o").isDefined must_== true
      options.get("k").isDefined must_== true
      options.get("opt").isDefined must_== true
      options.get("kopt").isDefined must_== true
      args.size must_== 1
      args(0) must_== "arg1"
    }

    "parse short option and then short flag option" in {
      val parser = new OptionParser
      parser.addOption("o")
      parser.addFlagOption("f")
      val (options, args) = parser.parseArgs(
        "-o"::"val"::"-f"::Nil)
      options.size must_== 2
      options.get("o") must_== Some("val")
      options.get("f").isDefined must_== true
      args.size must_== 0
    }

    "parse short flag option and then short option" in {
      val parser = new OptionParser
      parser.addOption("o")
      parser.addFlagOption("f")
      val (options, args) = parser.parseArgs(
        "-f"::"-o"::"val"::Nil)
      options.size must_== 2
      options.get("o") must_== Some("val")
      options.get("f").isDefined must_== true
      args.size must_== 0
    }
  }
}
